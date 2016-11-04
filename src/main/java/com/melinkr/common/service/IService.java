package com.melinkr.common.service;

import com.github.pagehelper.PageInfo;
import com.melinkr.common.model.BaseEntity;
import com.melinkr.common.model.PageModel;
import com.melinkr.common.model.PageModelForDatatable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a> on 2016/9/8.
 */
public interface IService<T> {

    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     * @param entity
     * @return
     */
    int saveOneWithNull(T entity);

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     * @param entity
     * @return
     */
    int saveOneNotNull(T entity);

    /**
     * 保存或更新
     * @param entity
     * @return
     */
    int saveOrUpdateNotNull(T entity);

    /**
     * 保存多个实体，存在一个限制是实体必须包含id属性并且为自增列
     * @param entitys
     * @return
     */
    int saveList(List<T> entitys);

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     * @param key
     * @return
     */
    int deleteByKey(Serializable key);

    /**
     * 根据实体中的属性进行删除，一次可删除0-n条记录
     * 该方法需要由各个mapper.xml自定义实现
     * @param entity
     * @return
     */
    int deleteList(T entity);

    int deleteListByKey(String ids);

    /**
     * 根据主键更新实体全部字段，null值会被更新
     * @param entity
     * @return
     */
    int updateByKeyWithNull(T entity);

    /**
     * 根据主键更新属性不为null的属性
     * @param entity
     * @return
     */
    int updateByKeyNotNull(T entity);

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
     * 查询全部结果，select(null)方法能达到同样的效果
     * @return
     */
    List<T> selectAll();

    /**
     * 根据条件分页查询
     * @param entity
     * @param pageModel
     * @return
     */
    PageInfo selectListWithPage(T entity, PageModel pageModel);

    /**
     * 分页查询所有
     * @param pageModel
     * @return
     */
    PageInfo selectAllWithPage(PageModel pageModel);

    /**
     * Datatables根据条件分页查询
     * @param entity
     * @param pageModel
     * @return
     */
    PageInfo selectListWithPageForDatatable(T entity, PageModelForDatatable pageModel);

    /**
     * Datatables根据条件模糊查询
     * @param pageModel
     * @return
     */
    PageInfo selectListWithPageForDatatable(PageModelForDatatable pageModel);

    /**
     * Datatables根据条件分页查询分页查询所有
     * @param pageModel
     * @return
     */
    PageInfo selectAllWithPageForDatatable(PageModelForDatatable pageModel);


}
