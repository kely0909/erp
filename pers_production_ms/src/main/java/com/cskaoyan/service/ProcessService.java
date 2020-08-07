package com.cskaoyan.service;

import com.cskaoyan.domain.PageBean;
import com.cskaoyan.domain.Process;

import java.util.List;

public interface ProcessService  {

    /**
     * 分页查找
     * @param page 当前页码
     * @param rows 条/页
     * @return != null 查找成功；null 查找失败
     */
    PageBean<Process> list(Integer page, Integer rows);

    /**
     *
     * @param process
     * @return 1表示插入成功
     */
    int insert(Process process);

    /**
     * 更新数据
     * @param process
     * @return
     */
    int update(Process process);

    /**
     * @param id
     * @return
     */
    int delete(String id);

    /**
     * @param searchValue  从前端传入的值
     * @param page
     * @param rows
     * @return
     */
    PageBean<Process> findProcessById(String searchValue, Integer page, Integer rows);

    /**
     * @param searchValue
     * @param page
     * @param rows
     * @return
     */
    PageBean<Process> findProcessByPlanId(String searchValue, Integer page, Integer rows);

    Process selectProcessById(String id);

    /**
     * 返回所有bean的数据
     *
     * @return
     */
    List<Process> selectAllProcess();
}
