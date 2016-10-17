package com.melinkr.common.utils;

import com.github.pagehelper.PageInfo;
import com.melinkr.common.model.PageModel;
import com.melinkr.common.model.PageModelForDatatable;
import com.melinkr.common.rpc.page.RpcPageInfoForDatatable;


/**
 * Created by miller(scofeild806@gmail.com) on 2016/9/30.
 */
public class PageInfoUtil<T> {
    public static RpcPageInfoForDatatable  toDatatablePage(PageInfo pageInfo, PageModelForDatatable pageModel){
        RpcPageInfoForDatatable rpcPageInfoForDatatable = new RpcPageInfoForDatatable(pageModel.getDraw(),pageInfo.getTotal(),pageInfo.getSize(),pageInfo.getList());
        return rpcPageInfoForDatatable;
    }
}