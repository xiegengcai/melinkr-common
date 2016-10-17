package com.melinkr.common.mapper;

import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeySelectiveMapper;

/**
 * Created by miller on 2016/9/19.
 */
public interface MelinkrUpdateMapper<T> extends UpdateByPrimaryKeyMapper<T>,UpdateByPrimaryKeySelectiveMapper<T> {
}
