package com.melinkr.common.model;

import com.melinkr.common.Constant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by miller(scofeild806@gmail.com) on 2016/9/27.
 */
public class PageModelForDatatable implements Serializable{
    private static final long serialVersionUID = 1L;

    private int draw;
    private int start;//第一条数据的起始位置，比如0代表第一条数据
    private int length;//告诉服务器每页显示的条数，这个数字会等于返回的 data集合的记录数
    private Map<Constant.Search,String> search;//接收通用搜索条件
    private List<Map<Constant.Order,String>> order = new ArrayList<Map<Constant.Order,String>>();
    private List<Map<Constant.Column, String>> columns = new ArrayList<Map<Constant.Column, String>>();
    private String advancedQuery;


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

    public Map<Constant.Search, String> getSearch() {
        return search;
    }

    public void setSearch(Map<Constant.Search, String> search) {
        this.search = search;
    }

    public List<Map<Constant.Order, String>> getOrder() {
        return order;
    }

    public void setOrder(List<Map<Constant.Order, String>> order) {
        this.order = order;
    }

    public List<Map<Constant.Column, String>> getColumns() {
        return columns;
    }

    public void setColumns(List<Map<Constant.Column, String>> columns) {
        this.columns = columns;
    }

    public String getAdvancedQuery() {
        return advancedQuery;
    }

    public void setAdvancedQuery(String advancedQuery) {
        this.advancedQuery = advancedQuery;
    }
}
