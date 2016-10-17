package com.melinkr.common.utils;

import com.melinkr.common.Constant;
import com.melinkr.common.poi.reader.Excel2003Reader;
import com.melinkr.common.poi.reader.Excel2007Reader;
import com.melinkr.common.poi.reader.ExcelReader;
import com.melinkr.common.poi.reader.IRowReader;
import com.melinkr.common.poi.writer.ExcelWriter;
import com.melinkr.common.poi.writer.IRowWriter;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a> on 16-10-15.
 */
public class ExcelUtil {

    private ExcelUtil(){}

    /**
     * 读取Excel文件
     * @param rowReader
     * @param fileName
     * @throws Exception
     */
    public static void readExcel(IRowReader rowReader, String fileName) throws Exception {
        create(rowReader, fileName).process(fileName);
    }

    /**
     * 读取Excel文件
     * @param rowReader
     * @param file
     * @throws Exception
     */
    public static void readExcel(IRowReader rowReader, File file) throws Exception {
        create(rowReader, file.getName()).process(file);
    }

    /**
     * 读取Excel文件
     * @param rowReader
     * @param fileName
     * @throws Exception
     */
    public static void readExcel(IRowReader rowReader, InputStream inputStream, String fileName) throws Exception {
        create(rowReader, fileName).process(inputStream);
    }

    /**
     * 创建ExcelReader，可能是03也可能是07版本
     * @param rowReader
     * @param fileName
     * @return
     * @throws Exception
     */
    private static ExcelReader create(IRowReader rowReader, String fileName) throws Exception{
        ExcelReader excelReader = null;
        // 处理excel2003文件
        if (fileName.endsWith(Constant.EXCEL03_EXTENSION)){
            excelReader = new Excel2003Reader();
            // 处理excel2007文件
        } else if (fileName.endsWith(Constant.EXCEL07_EXTENSION)){
            excelReader = new Excel2007Reader();
        } else {
            throw new  Exception("文件格式错误，fileName的扩展名只能是xls或xlsx。");
        }
        excelReader.setRowReader(rowReader);
        return excelReader;
    }

    /**
     * 带表头写入
     * @param fileName
     * @param rowWriter
     * @param headers
     * @param sheetDataMap
     * @param <T>
     * @throws Exception
     */
    public static <T> void writeExcel(String fileName, IRowWriter<T> rowWriter, List<String> headers, Map<String, List<T>> sheetDataMap) throws Exception {
        new ExcelWriter<T>(rowWriter, headers, sheetDataMap, fileName).process();
    }
    /**
     * 不带表头写入
     * @param fileName
     * @param rowWriter
     * @param sheetDataMap
     * @param <T>
     * @throws Exception
     */
    public static <T> void writeExcel(String fileName, IRowWriter<T> rowWriter, Map<String, List<T>> sheetDataMap) throws Exception {
        new ExcelWriter<T>(rowWriter, sheetDataMap, fileName).process();
    }

}
