package com.melinkr.common.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.melinkr.common.mapper.MelinkrMapper;
import com.melinkr.common.model.BaseEntity;
import com.melinkr.common.model.PageModel;
import com.melinkr.common.model.PageModelForDatatable;
import com.melinkr.common.model.ParamsForDatatable;
import com.melinkr.common.utils.DatatablesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a> on 2016/9/8.
 */
public abstract class BaseService<T extends BaseEntity, M extends MelinkrMapper<T>> implements IService<T> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected abstract M getMapper();

    @Override
    public int saveOneWithNull(T entity) {
        Preconditions.checkNotNull(entity, "传入实体不可为空");
        return getMapper().insert(entity);
    }

    @Override
    public int saveOneNotNull(T entity) {
        Preconditions.checkNotNull(entity, "传入实体不可为空");
        return getMapper().insertSelective(entity);
    }

    @Override
    public int saveOrUpdateNotNull(T entity) {
        if (entity.getId() != null) {
            return getMapper().updateByPrimaryKeySelective(entity);
        }
        return saveOneNotNull(entity);
    }

    @Override
    public int saveList(List<T> entitys) {
        int j = 0;
        for (int i = 0; i < entitys.size(); i++) {
            j++;
            getMapper().insert(entitys.get(i));
        }
        return j;
    }

    @Override
    public int deleteByKey(Serializable key) {
        Preconditions.checkNotNull(key, "主键不能为空");
        return getMapper().deleteByPrimaryKey(key);
    }



    @Override
    public int deleteList(T entity) {
        Preconditions.checkNotNull(entity, "传入实体不可为空");
        return getMapper().deleteList(entity);
    }

    @Override
    public int deleteListByKey(String ids) {
        String[] idArray = ids.split(",");
        int rows = 0;
        for (String id: idArray) {
            rows++;
            getMapper().deleteByPrimaryKey(Integer.parseInt(id));
        }
        return rows;
    }

    @Override
    public int updateByKeyWithNull(T entity) {
        Preconditions.checkNotNull(entity, "传入实体不可为空");
        return getMapper().updateByPrimaryKey(entity);
    }

    @Override
    public int updateByKeyNotNull(T entity) {
        Preconditions.checkNotNull(entity, "传入实体不可为空");
        return getMapper().updateByPrimaryKeySelective(entity);
    }

    @Override
    public T selectByKey(Serializable key) {
        return getMapper().selectByPrimaryKey(key);
    }

    @Override
    public T selectOne(T entity) {
        Preconditions.checkNotNull(entity, "传入实体不可为空");
        return getMapper().selectOne(entity);
    }

    @Override
    public List<T> selectList(T entity) {
        Preconditions.checkNotNull(entity, "传入实体不可为空");
        return getMapper().selectList(entity);
    }

    @Override
    public List<T> selectAll() {
        return getMapper().selectAll();
    }

    @Override
    public PageInfo<T> selectListWithPage(T entity, PageModel pageModel) {
        Preconditions.checkNotNull(entity, "传入实体不可为空");
        Preconditions.checkNotNull(pageModel, "分页信息不能为空");
        PageHelper.startPage(pageModel.getPageNum(), pageModel.getPageSize());
        return new PageInfo<>(getMapper().selectList(entity));
    }

    @Override
    public PageInfo<T> selectAllWithPage(PageModel pageModel) {
        Preconditions.checkNotNull(pageModel, "分页信息不能为空");
        PageHelper.startPage(pageModel.getPageNum(), pageModel.getPageSize());
        return new PageInfo<>(getMapper().selectAll());
    }

    @Override
    public PageInfo selectListWithPageForDatatable(T entity, PageModelForDatatable pageModel) {
        Preconditions.checkNotNull(entity, "传入实体不可为空");
        Preconditions.checkNotNull(pageModel, "分页信息不能为空");
        PageHelper.offsetPage(pageModel.getStart(), pageModel.getLength());
        return new PageInfo<>(getMapper().selectList(entity));
    }

    @Override
    public PageInfo selectListWithPageForDatatable(PageModelForDatatable pageModel){
        ParamsForDatatable paramsForDatatable = DatatablesUtil.toParams(pageModel);
        PageHelper.offsetPage(pageModel.getStart(), pageModel.getLength());
        return new PageInfo<>(getMapper().selectDataTable(paramsForDatatable));
    };

    @Override
    public PageInfo selectAllWithPageForDatatable(PageModelForDatatable pageModel) {
        Preconditions.checkNotNull(pageModel, "分页信息不能为空");
        PageHelper.offsetPage(pageModel.getStart(), pageModel.getLength());
        return new PageInfo<>(getMapper().selectAll());
    }

    //TODO 其他...
}
