package com.cskaoyan.dao;

import com.cskaoyan.domain.Device;
import com.cskaoyan.domain.DeviceCheck;
import com.cskaoyan.domain.DeviceExample;

import java.util.List;

public interface DeviceMapper {
    int deleteByPrimaryKey(String deviceId);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(String deviceId);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

    List<Device> selectALL();

    // custom模块增加
    List<Device> selectByExample(DeviceExample example);

    List<DeviceCheck> searchByName(String searchValue);

}