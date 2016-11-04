package com.melinkr.common.rpc;

import com.melinkr.common.model.PageModel;
import com.melinkr.common.model.PageModelForDatatable;
import com.melinkr.common.rpc.page.RpcPageInfo;
import com.melinkr.common.rpc.page.RpcPageInfoForDatatable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a> on 2016/9/20.
 */
public interface IRpcService<T> {

    /**
     * 保存一个实体
     * @param entity
     * @return
     */
    int save(T entity);


    /**
     * 保存多个实体，存在一个限制是实体必须包含id属性并且为自增列
     * @param entitys
     * @return
     */
    int saveList(List<T> entitys);

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     * @param keys
     * @return
     */
    int deleteByKey(Serializable ... keys);

    /**
     * 根据主键字段删除,传入的是string的ids
     * @param ids
     * @return
     */
    int deleteListByKey(String ids);
    /**
     * 根据主键更新属性
     * @param entity
     * @return
     */
    int updateByKey(T entity);

    /**
     * id为空时增加,id非空时修改
     * @param entity
     * @return
     */
    int saveOrUpdateOneNotNull(T entity);
    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     * @param key
     * @return
     */
    T selectByKey(Serializable key);

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     * @param entity
     * @return
     */
    T selectOne(T entity);

    /**
     * 根据实体属性查询，返回多个值(0-n)
     * 该方法需要由各个mapper.xml自定义实现
     * @param entity
     * @return
     */
    List<T> selectList(T entity);


    /**
     * 根据条件分页查询
     * @param entity
     * @param pageModel
     * @return
     */
    RpcPageInfo<T> selectListWithPage(T entity, PageModel pageModel);

    /**
     * 分页查询所有
     * @param pageModel
     * @return
     */
    RpcPageInfo<T> selectAllWithPage(PageModel pageModel);

    /**
     * 根据条件分页查询
     * @param entity
     * @param pageModel
     * @return
     */
    RpcPageInfoForDatatable<T> selectListWithPageForDatatable(T entity, PageModelForDatatable pageModel);

    /**
     * 根据条件模糊查询
     * @param pageModel
     * @return
     */
    RpcPageInfoForDatatable<T> selectListWithPageForDatatable(PageModelForDatatable pageModel);

    /**
     * 分页查询所有
     * @param pageModel
     * @return
     */
    RpcPageInfoForDatatable<T> selectAllWithPageForDatatable(PageModelForDatatable pageModel);
}
