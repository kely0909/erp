package com.cskaoyan.dao;

import com.cskaoyan.domain.FinalCountCheck;
import com.cskaoyan.domain.ProcessCountCheck;

import java.util.HashMap;
import java.util.List;

public interface FinalCountCheckMapper {
    int deleteByPrimaryKey(String fCountCheckId);

    int insert(FinalCountCheck record);

    int insertSelective(FinalCountCheck record);

    FinalCountCheck selectByPrimaryKey(String fCountCheckId);

    int updateByPrimaryKeySelective(FinalCountCheck record);

    int updateByPrimaryKey(FinalCountCheck record);

    List<ProcessCountCheck> selectAll();

    int deleteByIds(String[] ids);

    List<ProcessCountCheck> selectCountChecksByPrimaryKeyId(HashMap<String, String> map);

    List<ProcessCountCheck> selectOrdersByPrimaryKeyId(HashMap<String, String> map);
}