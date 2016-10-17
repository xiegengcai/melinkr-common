package com.melinkr.common.poi.reader;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 抽象Excel2007读取器，excel2007的底层数据结构是xml文件，采用SAX的事件驱动的方法解析
 * xml，需要继承DefaultHandler，在遇到文件内容时，事件会触发，这种做法可以大大降低
 * 内存的耗费，特别使用于大数据量的文件。
 * Created by <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a> on 16-10-15.
 */
public class Excel2007Reader extends DefaultHandler implements ExcelReader {
    private final static String SAXPARSER = "org.apache.xerces.parsers.SAXParser";
    //共享字符串表
    private SharedStringsTable sst;
    //上一次的内容
    private String lastContents;
    private boolean nextIsString;

    private int sheetIndex = -1;
    private List<String> rowlist = new ArrayList<String>();
    //当前行
    private int curRow = 0;
    //当前列
    private int curCol = 0;
    //日期标志
    private boolean dateFlag;
    //数字标志
    private boolean numberFlag;

    private boolean isTElement;

    private IRowReader rowReader;


    @Override
    public void setRowReader(IRowReader rowReader) {
        this.rowReader = rowReader;
    }

    @Override
    public void process(String fileName) throws Exception {
        process0(OPCPackage.open(fileName));
    }

    @Override
    public void process(File file) throws Exception {
        process0(OPCPackage.open(file));
    }

    @Override
    public void process(InputStream inputStream) throws Exception {
        process0(OPCPackage.open(inputStream));
    }

    private void process0(OPCPackage opcPackage) throws  Exception{
        XSSFReader xssfReader = new XSSFReader(opcPackage);
        this.sst = xssfReader.getSharedStringsTable();
        XMLReader xmlReader = XMLReaderFactory.createXMLReader(SAXPARSER);
        xmlReader.setContentHandler(this);
        Iterator<InputStream> sheets = xssfReader.getSheetsData();
        while (sheets.hasNext()) {
            curRow = 0;
            sheetIndex ++;
            InputStream sheet = sheets.next();
            InputSource sheetSource = new InputSource(sheet);
            xmlReader.parse(sheetSource);
            sheet.close();
        }
    }

    public void startElement(String uri, String localName, String name,
                             Attributes attributes) throws SAXException {

        // c => 单元格
        if ("c".equals(name)) {
            // 如果下一个元素是 SST 的索引，则将nextIsString标记为true
            String cellType = attributes.getValue("t");
            if ("s".equals(cellType)) {
                nextIsString = true;
            } else {
                nextIsString = false;
            }
            //日期格式
            String cellDateType = attributes.getValue("s");
            if ("1".equals(cellDateType)){
                dateFlag = true;
            } else {
                dateFlag = false;
            }
            String cellNumberType = attributes.getValue("s");
            if("2".equals(cellNumberType)){
                numberFlag = true;
            } else {
                numberFlag = false;
            }

        }
        //当元素为t时
        if("t".equals(name)){
            isTElement = true;
        } else {
            isTElement = false;
        }

        // 置空
        lastContents = "";
    }

    public void endElement(String uri, String localName, String name)
            throws SAXException {

        // 根据SST的索引值的到单元格的真正要存储的字符串
        // 这时characters()方法可能会被调用多次
        if (nextIsString) {
            try {
                int idx = Integer.parseInt(lastContents);
                lastContents = new XSSFRichTextString(sst.getEntryAt(idx))
                        .toString();
            } catch (Exception e) {

            }
        }
        //t元素也包含字符串
        if(isTElement){
            String value = lastContents.trim();
            rowlist.add(curCol, value);
            curCol++;
            isTElement = false;
            // v => 单元格的值，如果单元格是字符串则v标签的值为该字符串在SST中的索引
            // 将单元格内容加入rowlist中，在这之前先去掉字符串前后的空白符
        } else if ("v".equals(name)) {
            String value = lastContents.trim();
            value = value.equals("")?" ":value;
            //日期格式处理
            if(dateFlag){
                Date date = HSSFDateUtil.getJavaDate(Double.valueOf(value));
                value = DateFormatUtils.format(date, DateFormatUtils.ISO_DATETIME_FORMAT.getPattern());
            }
            //数字类型处理
            if(numberFlag){
                BigDecimal bd = new BigDecimal(value);
                value = bd.setScale(3, BigDecimal.ROUND_UP).toString();
            }
            rowlist.add(curCol, value);
            curCol++;
        }else {
            //如果标签名称为 row ，这说明已到行尾，调用 optRows() 方法
            if (name.equals("row")) {
                rowReader.getRows(sheetIndex,curRow,rowlist);
                rowlist.clear();
                curRow++;
                curCol = 0;
            }
        }

    }

    public void characters(char[] ch, int start, int length)
            throws SAXException {
        //得到单元格内容的值
        lastContents += new String(ch, start, length);
    }

}
