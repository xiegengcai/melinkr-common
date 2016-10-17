package com.melinkr.common.mapper;

import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;
import tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * Created by miller on 2016/9/19.
 *
 */
public interface MelinkrInsertMapper<T> extends InsertMapper<T>,InsertSelectiveMapper<T>,MySqlMapper<T> {
}
