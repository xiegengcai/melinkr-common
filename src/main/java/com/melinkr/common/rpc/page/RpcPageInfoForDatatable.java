package com.melinkr.common.rpc.page;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *     com.github.pagehelper.PageInfo简化版，用来RPC传输
 * </pre>
 * Created by <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a> on 2016/9/9.
 */
public class RpcPageInfoForDatatable<T> implements Serializable {
    private static final long serialVersionUID = -3370818573565991895L;
    private long draw;
    private long recordsTotal;
    private long recordsFiltered;
    private List<T> data;
    private String error;

    public RpcPageInfoForDatatable() {
    }

    public RpcPageInfoForDatatable(long draw, long recordsTotal, long recordsFiltered, List<T> data) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getDraw() {
        return draw;
    }

    public void setDraw(long draw) {
        this.draw = draw;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
