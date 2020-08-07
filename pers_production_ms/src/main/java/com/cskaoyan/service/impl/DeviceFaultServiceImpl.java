package com.cskaoyan.service.impl;

import com.cskaoyan.dao.DeviceCheckMapper;
import com.cskaoyan.dao.DeviceFaultMapper;
import com.cskaoyan.dao.DeviceMapper;
import com.cskaoyan.dao.DeviceTypeMapper;
import com.cskaoyan.domain.*;
import com.cskaoyan.service.DeviceFaultService;
import com.cskaoyan.service.DeviceTypeService;
import com.cskaoyan.util.convert.MyDeviceConverter;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceFaultServiceImpl implements DeviceFaultService {
    @Autowired
    DeviceFaultMapper deviceFaultMapper;

    @Autowired
    MyDeviceConverter myDeviceConverter;

    @Autowired
    DeviceTypeMapper deviceTypeMapper;

    @Autowired
    DeviceMapper deviceMapper;

    @Autowired
    DeviceCheckMapper deviceCheckMapper;

    @Override
    public PageBean<DeviceFaultExt> list(Integer page, Integer rows) {
        PageHelper.startPage(page, rows, true);
        List<DeviceFault> deviceFaults = deviceFaultMapper.selectALL();

        System.out.println(deviceFaults);

        PageBean pageBean = new PageBean<>(deviceFaults);

        List<DeviceFaultExt> list = new ArrayList<>();
        for (DeviceFault device : deviceFaults) {
            DeviceFaultExt convert = new DeviceFaultExt();
            convert = (DeviceFaultExt) myDeviceConverter.convert(device, convert);

            String deviceTypeId = device.getDeviceId();

            if (deviceTypeId != null) {
                Device device1 = deviceMapper.selectByPrimaryKey(deviceTypeId);

                String deviceName = device1.getDeviceName();

                convert.setDeviceName(deviceName);

            }
            list.add(convert);
        }
        pageBean.setRows(list);
        return pageBean;
    }

    @Override
    public DeviceFault selectByPrimaryKey(String key) {
        return deviceFaultMapper.selectByPrimaryKey(key);
    }
}
