package com.melinkr.common.mapper;

import com.melinkr.common.model.ParamsForDatatable;

import java.util.List;

/**
 * Created by miller on 2016/9/19.
 * 在生成的mapper中需要实现本类中方法，之后才能使用service中同名方法
 */
public interface MelinkrDefined<T> {
    int deleteList(T entity);
    List<T> selectList(T entity);
    List<T> selectDataTable(ParamsForDatatable paramsForDatatable);
}
