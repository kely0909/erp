package com.cskaoyan.dao;

import com.cskaoyan.domain.Device;
import com.cskaoyan.domain.DeviceCheck;

import java.util.List;

public interface DeviceCheckMapper {
    int deleteByPrimaryKey(String deviceCheckId);

    int insert(DeviceCheck record);

    int insertSelective(DeviceCheck record);

    DeviceCheck selectByPrimaryKey(String deviceCheckId);

    int updateByPrimaryKeySelective(DeviceCheck record);

    int updateByPrimaryKey(DeviceCheck record);

    List<DeviceCheck> selectALL();

    List<DeviceCheck> findbydeviceCheckId(String deviceCheckID);

    List<DeviceCheck> searchByName(String searchValue);
}