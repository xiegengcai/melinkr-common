package com.melinkr.common.mapper;

import tk.mybatis.mapper.common.base.select.SelectAllMapper;
import tk.mybatis.mapper.common.base.select.SelectByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.select.SelectOneMapper;

/**
 * Created by miller on 2016/9/19.
 */
public interface MelinkrSelectMapper<T> extends SelectByPrimaryKeyMapper<T>,SelectAllMapper<T>,SelectOneMapper<T> {
}
