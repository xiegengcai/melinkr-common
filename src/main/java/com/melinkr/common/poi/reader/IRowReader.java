package com.melinkr.common.poi.reader;

import java.util.List;

/**
 * Created by <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a> on 16-10-15.
 */
public interface IRowReader {
    void getRows(int sheetIndex, int curRow, List<String> rowlist);
}
