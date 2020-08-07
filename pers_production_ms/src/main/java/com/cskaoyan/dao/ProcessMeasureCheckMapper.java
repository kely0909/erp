package com.cskaoyan.dao;

import com.cskaoyan.domain.ProcessCountCheck;
import com.cskaoyan.domain.ProcessMeasureCheck;

import java.util.HashMap;
import java.util.List;

public interface ProcessMeasureCheckMapper {
    int deleteByPrimaryKey(String pMeasureCheckId);

    int insert(ProcessMeasureCheck record);

    int insertSelective(ProcessMeasureCheck record);

    ProcessMeasureCheck selectByPrimaryKey(String pMeasureCheckId);

    int updateByPrimaryKeySelective(ProcessMeasureCheck record);

    int updateByPrimaryKey(ProcessMeasureCheck record);

    List<ProcessCountCheck> selectAll();

    List<ProcessCountCheck> selectProcessMeasureChecksByPrimaryKeyId(HashMap<String, String> map);

    int deleteByIds(String[] ids);
}