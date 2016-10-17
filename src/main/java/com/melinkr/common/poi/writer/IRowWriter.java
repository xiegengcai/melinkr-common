package com.melinkr.common.poi.writer;

import org.apache.poi.ss.usermodel.Row;

import java.util.List;

/**
 * Created by <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a> on 16-10-17.
 */
public interface IRowWriter<T> {

    /**
     * 写表头
     * @param row
     * @param headers
     */
    default void writeHeader(Row row, List<String> headers){
        for (int i = 0; i < headers.size(); i++) {
            row.createCell(i).setCellValue(headers.get(i));
        }
    }
    /**
     * 写入行数据
     * @param row
     * @param data
     */
    void writeRow(Row row, T data);

}
