package com.melinkr.common.poi.writer;

import com.melinkr.common.Constant;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

/**
 * 写Excel实现
 * Created by <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a> on 16-10-17.
 */
public class ExcelWriter<T>  {
    protected IRowWriter<T> rowWriter;
    protected List<String> headers;
    protected Map<String, List<T>> sheetDataMap;
    protected String fileName;

    public ExcelWriter() {
    }

    public ExcelWriter(IRowWriter<T> rowWriter, Map<String, List<T>> sheetDataMap, String fileName) {
        this(rowWriter, null, sheetDataMap, fileName);
    }

    public ExcelWriter(IRowWriter<T> rowWriter, List<String> headers, Map<String, List<T>> sheetDataMap, String fileName) {
        this.rowWriter = rowWriter;
        this.headers = headers;
        this.sheetDataMap = sheetDataMap;
        this.fileName = fileName;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setSheetDataMap(Map<String, List<T>> sheetDataMap) {
        this.sheetDataMap = sheetDataMap;
    }

    public void setRowWriter(IRowWriter<T> rowWriter) {
        this.rowWriter = rowWriter;
    }


    public void createSheet(Workbook workbook, String sheetName, List<T> sheetData) {
        Sheet sheet = workbook.createSheet(sheetName);
        int rowIndex = 0;
        if (this.headers != null) {
            //写header
            rowWriter.writeHeader(sheet.createRow(rowIndex++), this.headers);
        }
        for(T data : sheetData) {
            rowWriter.writeRow(sheet.createRow(rowIndex++), data);
        }
    }

    /**
     * 写工作薄
     * @throws Exception
     */
    public void process() throws Exception {
        // 建立工作簿和电子表格对象
        Workbook workbook = null;
        if (fileName.endsWith(Constant.EXCEL03_EXTENSION)){
            workbook = new HSSFWorkbook();
        } else if (fileName.endsWith(Constant.EXCEL07_EXTENSION)){
            workbook = new XSSFWorkbook();
        } else {
            throw new  Exception("文件格式错误，fileName的扩展名只能是xls或xlsx。");
        }

        if (sheetDataMap != null){
            for (Map.Entry<String, List<T>> entry:sheetDataMap.entrySet()) {
                createSheet(workbook, entry.getKey(), entry.getValue());
            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

}
