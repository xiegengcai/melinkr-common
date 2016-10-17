package com.melinkr.common.rpc;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.melinkr.common.model.PageModel;
import com.melinkr.common.model.PageModelForDatatable;
import com.melinkr.common.rpc.page.RpcPageInfo;
import com.melinkr.common.rpc.page.RpcPageInfoForDatatable;
import com.melinkr.common.service.IService;
import com.melinkr.common.utils.PageInfoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by miller on 2016/9/18.
 */
public abstract class BaseRpcService<T, S extends IService<T>> implements IRpcService<T> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected abstract S getService();

    @Override
    public int save(T entity) {
        return getService().saveOneNotNull(entity);
    }

    @Override
    public int saveList(List<T> entitys) {
        return getService().saveList(entitys);
    }

    @Override
    public int deleteByKey(Serializable... keys) {
        Preconditions.checkNotNull(keys, "key不能为空");
        Preconditions.checkArgument(keys.length > 0, "key长度必须大于0");
        int rows = 0;
        for (Serializable k : keys) {
            getService().deleteByKey(k);
            rows ++;
        }
        return rows;
    }

    @Override
    public int updateByKey(T entity) {
        return getService().updateByKeyNotNull(entity);
    }

    @Override
    public T selectByKey(Serializable key) {
        return null;
    }

    @Override
    public T selectOne(T entity) {
        return getService().selectOne(entity);
    }

    @Override
    public List<T> selectList(T entity) {
        return getService().selectList(entity);
    }

    @Override
    public RpcPageInfo<T> selectListWithPage(T entity, PageModel pageModel) {
        RpcPageInfo<T> rpcPageInfo = new RpcPageInfo<T>();
        PageInfo<T> pageInfo = getService().selectListWithPage(entity, pageModel);
        BeanUtils.copyProperties(pageInfo, rpcPageInfo);
        return rpcPageInfo;
    }

    @Override
    public RpcPageInfo<T> selectAllWithPage(PageModel pageModel) {
        RpcPageInfo<T> rpcPageInfo = new RpcPageInfo<T>();
        PageInfo<T> pageInfo = getService().selectAllWithPage(pageModel);
        BeanUtils.copyProperties(pageInfo, rpcPageInfo);
        return rpcPageInfo;
    }

    @Override
    public RpcPageInfoForDatatable<T> selectListWithPageForDatatable(T entity, PageModelForDatatable pageModel) {
        PageInfo<T> pageInfo = getService().selectListWithPageForDatatable(entity, pageModel);
        return PageInfoUtil.toDatatablePage(pageInfo,pageModel);
    }

    @Override
    public RpcPageInfoForDatatable<T> selectAllWithPageForDatatable(PageModelForDatatable pageModel) {
        PageInfo<T> pageInfo = getService().selectAllWithPageForDatatable(pageModel);
        return PageInfoUtil.toDatatablePage(pageInfo,pageModel);
    }
}
