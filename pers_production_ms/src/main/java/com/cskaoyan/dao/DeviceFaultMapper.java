package com.cskaoyan.dao;

import com.cskaoyan.domain.Device;
import com.cskaoyan.domain.DeviceFault;

import java.util.List;

public interface DeviceFaultMapper {
    int deleteByPrimaryKey(String deviceFaultId);

    int insert(DeviceFault record);

    int insertSelective(DeviceFault record);

    DeviceFault selectByPrimaryKey(String deviceFaultId);

    int updateByPrimaryKeySelective(DeviceFault record);

    int updateByPrimaryKey(DeviceFault record);

    List<DeviceFault> selectALL();

}