package com.cskaoyan.dao;

import com.cskaoyan.domain.DeviceType;
import com.cskaoyan.domain.DeviceTypeExample;

import java.util.List;

public interface DeviceTypeMapper {
    int deleteByPrimaryKey(String[] deviceTypeId);

    int insert(DeviceType record);

    int insertSelective(DeviceType record);

    DeviceType selectByPrimaryKey(String deviceTypeId);

    int updateByPrimaryKeySelective(DeviceType record);

    int updateByPrimaryKey(DeviceType record);

    List<DeviceType> selectAll();


    void updateByPrimaryKeySelective();

    List<DeviceType> selectByExample(DeviceTypeExample example);
}