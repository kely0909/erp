package com.cskaoyan.dao;

import com.cskaoyan.domain.ProcessCountCheck;

import java.util.HashMap;
import java.util.List;

public interface ProcessCountCheckMapper {
    int deleteByPrimaryKey(String pCountCheckId);

    int insert(ProcessCountCheck record);

    int insertSelective(ProcessCountCheck record);

    ProcessCountCheck selectByPrimaryKey(String pCountCheckId);

    int updateByPrimaryKeySelective(ProcessCountCheck record);

    int updateByPrimaryKey(ProcessCountCheck record);

    List<ProcessCountCheck> selectAll();

    List<ProcessCountCheck> selectProcessCountChecksByPrimaryKeyId(HashMap<String, String> map);

    int deleteByIds(String[] ids);
}