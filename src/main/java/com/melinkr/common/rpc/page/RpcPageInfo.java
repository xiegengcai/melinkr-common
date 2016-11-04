package com.melinkr.common.rpc.page;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *     com.github.pagehelper.PageInfo简化版，用来RPC传输
 * </pre>
 * Created by <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a> on 2016/9/9.
 */
public class RpcPageInfo<T> implements Serializable {
    private static final long serialVersionUID = -3370818573565991895L;
    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;
    @JSONField(name = "data")
    private List<T> list;
    //总记录数
    private long total;
    //总页数
    private int pages;

    public RpcPageInfo() {
    }

    public RpcPageInfo(int pageNum, int pageSize, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.list = list;
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

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
