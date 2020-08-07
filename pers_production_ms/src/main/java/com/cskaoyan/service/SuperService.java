package com.cskaoyan.service;

import com.cskaoyan.domain.PageBean;

/**
 * @author Chen Xiaoyu
 * @author Sayen Cool
 */
public interface SuperService {

    /**
     * 自定义精确查询条件，根据条件查找全部，不分页
     *
     * @param domain
     * @param name
     * @param searchValue
     * @return
     * @throws Object
     */
    Object selectByCustom(String domain, String name, String searchValue) throws Exception;

    /**
     * @param domain
     * @param id
     * @return
     * @throws Exception
     */
    Object selectByPrimaryKey(String domain, String id) throws Exception;

    /**
     * @param domain
     * @param object
     * @return
     */
    Integer insert(String domain, Object object) throws Exception;

    /**
     * 查找全部信息
     *
     * @param domain 名称
     * @return 1：成功；0 失败
     */
    Object findAll(String domain) throws Exception;

    /**
     * 更新
     *
     * @param object 封装客户信息
     * @return 1：成功；0 失败
     */
    Integer updateByPrimaryKey(String manager, Object object) throws Exception;

    /**
     * 按主键数组批量删除数据
     *
     * @param ids 主键数组
     * @return >=1：成功；0：失败
     */
    Integer deleteByPrimaryKeyS(String domain, String[] ids) throws Exception;

    /**
     * @param domain
     * @param domain
     * @param ids
     * @return
     * @throws Exception
     */
    Integer deleteByCustom(String domain, String ids) throws Exception;

    /**
     * @param manager
     * @param name
     * @param searchValue
     * @param page
     * @param rows
     * @return
     */
    PageBean findBySearchCondition(String manager, String name, String searchValue, Integer page, Integer rows) throws Exception;
}
