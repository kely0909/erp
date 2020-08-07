package com.cskaoyan.dao;

import com.cskaoyan.domain.Custom;
import com.cskaoyan.domain.CustomExample;
import com.cskaoyan.domain.Process;
import com.cskaoyan.domain.ProcessExample;
import com.cskaoyan.domain.Product;
import com.cskaoyan.domain.ProductExample;
import com.cskaoyan.domain.TechnologyPlan;

import java.util.List;

public interface ProcessMapper {
    int deleteByPrimaryKey(String processId);

    int insert(Process record);
   

    int insertSelective(Process record);
  

    Process selectByPrimaryKey(String processId);
    

    int updateByPrimaryKeySelective(Process record);
    

    int updateByPrimaryKey(Process record);
    

    List<Process> selectAll();

    // customģ������
    List<Process> selectByExample(ProcessExample example);
    

    List<Process> selectById(String searchValue);
    

    List<Process> selectByTechnologyPlanId(String searchValue);
   
}