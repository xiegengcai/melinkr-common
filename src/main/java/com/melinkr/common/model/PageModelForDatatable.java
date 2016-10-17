package com.melinkr.common.model;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by miller(scofeild806@gmail.com) on 2016/9/27.
 */
public class PageModelForDatatable implements Serializable{
    private static final long serialVersionUID = 1L;

    private int draw;
    private int start;//第一条数据的起始位置，比如0代表第一条数据
    private int length;//告诉服务器每页显示的条数，这个数字会等于返回的 data集合的记录数
    private Map search;//接收通用搜索条件


    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Map getSearch() {
        return search;
    }

    public void setSearch(Map search) {
        this.search = search;
    }
}
