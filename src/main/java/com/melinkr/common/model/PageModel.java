package com.melinkr.common.model;

import java.io.Serializable;

/**
 * Created by miller on 2016/9/19.
 */
public class PageModel implements Serializable {

    private static final long serialVersionUID = 2789105581143319881L;

    private int pageNum;
    private int pageSize;


    public PageModel(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
