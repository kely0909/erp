package com.cskaoyan.service.impl;

import com.cskaoyan.dao.ProcessMapper;
import com.cskaoyan.dao.TechnologyPlanMapper;
import com.cskaoyan.domain.PageBean;
import com.cskaoyan.domain.Process;
import com.cskaoyan.domain.TechnologyPlan;
import com.cskaoyan.service.ProcessService;
import com.github.pagehelper.PageHelper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    ProcessMapper processMapper;

    private static Logger logger = LogManager.getLogger(ProcessServiceImpl.class);

    @Autowired
    TechnologyPlanMapper technologyPlanMapper;
    /**
     * 分页查找
     *
     * @param page 当前页码
     * @param rows 条/页
     * @return != null 查找成功；null 查找失败
     */
    @Override
    public PageBean<Process> list(Integer page, Integer rows) {
        PageHelper.startPage(page, rows, true);
        List<Process> processes = processMapper.selectAll();

        for(Process pr:processes){
            pr.setTechnologyPlan(technologyPlanMapper.selectByPrimaryKey(pr.getTechnologyPlanId()));
        }

        PageBean<Process> pageBean = new PageBean<>(processes);
        logger.info("pageBean = " + pageBean);
        return pageBean;
    }

    /**
     * @param process
     * @return 1表示插入成功
     */
    @Override
    public int insert(Process process) {
        return processMapper.insert(process);
    }

    /**
     * 更新数据
     *
     * @param process
     * @return
     */
    @Override
    public int update(Process process) {
        return processMapper.updateByPrimaryKey(process);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public int delete(String id) {
        return processMapper.deleteByPrimaryKey(id);
    }

    /**
     * @param searchValue 从前端传入的值
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageBean<Process> findProcessById(String searchValue, Integer page, Integer rows) {
        searchValue = "%" + searchValue + "%";
        PageHelper.startPage(page, rows, true);
        List<Process> processes = processMapper.selectById(searchValue);

        for(Process pr:processes){
            pr.setTechnologyPlan(technologyPlanMapper.selectByPrimaryKey(pr.getTechnologyPlanId()));
        }

        PageBean<Process> pageBean = new PageBean<>(processes);
        logger.info("pageBean = " + pageBean);
        return pageBean;

    }

    /**
     * @param searchValue
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageBean<Process> findProcessByPlanId(String searchValue, Integer page, Integer rows) {
        searchValue = "%" + searchValue + "%";
        PageHelper.startPage(page, rows, true);
        List<Process> processes = processMapper.selectByTechnologyPlanId(searchValue);

        for(Process pr:processes){
            pr.setTechnologyPlan(technologyPlanMapper.selectByPrimaryKey(pr.getTechnologyPlanId()));
        }

        PageBean<Process> pageBean = new PageBean<>(processes);
        logger.info("pageBean = " + pageBean);
        return pageBean;
    }

    @Override
    public Process selectProcessById(String id) {
        return processMapper.selectByPrimaryKey(id);
    }

    /**
     * 返回所有bean的数据
     *
     * @return
     */
    @Override
    public List<Process> selectAllProcess() {
        return processMapper.selectAll();
    }

}
