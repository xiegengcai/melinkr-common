package com.melinkr.common.mapper;

import tk.mybatis.mapper.common.base.delete.DeleteByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.delete.DeleteMapper;

/**
 * Created by miller on 2016/9/19.
 */
public interface MelinkrDeleteMapper<T> extends DeleteMapper<T>,DeleteByPrimaryKeyMapper<T> {
}
