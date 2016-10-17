package com.melinkr.common;

/**
 * 常量类,所有常量集中管理
 * Created by miller on 2016/9/7.
 */
public interface Constant {
    int PAGE_SIZE = 20;//默认每页展示数
    //excel2003扩展名
    String EXCEL03_EXTENSION = ".xls";
    //excel2007扩展名
    String EXCEL07_EXTENSION = ".xlsx";

    public enum LikeType {
        PRE_MATCH, POST_MATCH, BOTH;
    }

    /**
     * 通用状态枚举
     */
    public enum CommonStatus {
        INVALID     // 0 无效
        , VALID     // 1 有效
        ;
    }
}