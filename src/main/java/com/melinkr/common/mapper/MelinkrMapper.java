package com.melinkr.common.mapper;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * Created by <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a> on 2016/9/8.
 */
public interface MelinkrMapper <T> extends MelinkrInsertMapper<T>,MelinkrDeleteMapper<T>,MelinkrUpdateMapper<T>,MelinkrSelectMapper<T>,MelinkrDefined<T>{

}
