package com.melinkr.common.poi.reader;

import java.io.File;
import java.io.InputStream;

/**
 * Created by <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a> on 16-10-15.
 */
public interface ExcelReader {

    void setRowReader(IRowReader rowReader);

    /**
     * 以文件名的方式遍历excel下所有的sheet
     * @param fileName
     * @throws Exception
     */
    void process(String fileName) throws Exception;

    /**
     * 以文件的方式遍历excel下所有的sheet
     * @param file
     * @throws Exception
     */
    void process(File file) throws Exception;

    /**
     * 以流的方式遍历excel下所有的sheet
     * @param inputStream
     * @throws Exception
     */
    void process(InputStream inputStream) throws Exception;
}
