package com.cskaoyan.dao;

import com.cskaoyan.domain.FinalMeasuretCheck;
import com.cskaoyan.domain.ProcessCountCheck;

import java.util.HashMap;
import java.util.List;

public interface FinalMeasuretCheckMapper {
    int deleteByPrimaryKey(String fMeasureCheckId);

    int insert(FinalMeasuretCheck record);

    int insertSelective(FinalMeasuretCheck record);

    FinalMeasuretCheck selectByPrimaryKey(String fMeasureCheckId);

    int updateByPrimaryKeySelective(FinalMeasuretCheck record);

    int updateByPrimaryKey(FinalMeasuretCheck record);

    List<ProcessCountCheck> selectAll();

    int deleteByIds(String[] ids);

    List<ProcessCountCheck> selectFinalMeasuretChecksByPrimaryKeyId(HashMap<String, String> map);

    List<ProcessCountCheck> selectFinalMeasuretChecksByPrimaryKeyOrderId(HashMap<String, String> map);
}