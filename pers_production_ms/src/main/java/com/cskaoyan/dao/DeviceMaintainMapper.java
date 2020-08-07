package com.cskaoyan.dao;

import com.cskaoyan.domain.DeviceFault;
import com.cskaoyan.domain.DeviceMaintain;

import java.util.List;

public interface DeviceMaintainMapper {
    int deleteByPrimaryKey(String deviceMaintainId);

    int insert(DeviceMaintain record);

    int insertSelective(DeviceMaintain record);

    DeviceMaintain selectByPrimaryKey(String deviceMaintainId);

    int updateByPrimaryKeySelective(DeviceMaintain record);

    int updateByPrimaryKey(DeviceMaintain record);

    List<DeviceMaintain> selectALL();


}