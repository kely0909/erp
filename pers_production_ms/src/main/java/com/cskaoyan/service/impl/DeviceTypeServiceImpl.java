package com.cskaoyan.service.impl;

import com.cskaoyan.dao.DeviceCheckMapper;
import com.cskaoyan.dao.DeviceMapper;
import com.cskaoyan.dao.DeviceTypeMapper;
import com.cskaoyan.domain.Device;
import com.cskaoyan.domain.DeviceCheck;
import com.cskaoyan.domain.DeviceType;
import com.cskaoyan.service.DeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class DeviceTypeServiceImpl implements DeviceTypeService {

    @Autowired
    DeviceTypeMapper deviceTypeMapper;

    @Autowired
    DeviceMapper deviceMapper;

    @Autowired
    DeviceCheckMapper deviceCheckMapper;

    @Override
    public List<DeviceType> Typeslist(Integer page, Integer rows) {
        return deviceTypeMapper.selectAll();
    }




    @Override
    public int Devicetypeinsert(DeviceType deviceType) {

        int insert = deviceTypeMapper.insert(deviceType);
        return insert;
    }

    @Override
    public int Devicetypeupdate(DeviceType deviceType){

        int i = deviceTypeMapper.updateByPrimaryKeySelective(deviceType);
        return i;
    }

    @Override
    public int Devicetypedelete(String[] ids) {
        int i = deviceTypeMapper.deleteByPrimaryKey(ids);
        return i;
    }

    @Override
    public DeviceType findDeviceTypebyid(String id) {
        return deviceTypeMapper.selectByPrimaryKey(id);
    }


}
