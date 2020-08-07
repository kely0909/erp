package com.cskaoyan.service.impl;

import com.cskaoyan.dao.*;
import com.cskaoyan.domain.Device;
import com.cskaoyan.domain.DeviceMaintain;
import com.cskaoyan.domain.Employee;
import com.cskaoyan.domain.PageBean;
import com.cskaoyan.service.DeviceMaintainService;
import com.cskaoyan.util.convert.MyDeviceConverter;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceMaintainServiceImpl implements DeviceMaintainService {
    @Autowired
    DeviceTypeMapper deviceTypeMapper;

    @Autowired
    DeviceMapper deviceMapper;

    @Autowired
    DeviceCheckMapper deviceCheckMapper;

    @Autowired
    DeviceMaintainMapper deviceMaintainMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public PageBean<DeviceMaintain> list(Integer page, Integer rows) {

        PageHelper.startPage(page, rows, true);
        List<DeviceMaintain> devices = deviceMaintainMapper.selectALL();
        PageBean pageBean = new PageBean<>(devices);
        ArrayList<DeviceMaintain> list = new ArrayList<>();
        for (DeviceMaintain device : devices) {
            String deviceMaintainEmpId = device.getDeviceMaintainEmpId();
            Employee employee = employeeMapper.selectByPrimaryKey(deviceMaintainEmpId);
            String empName = employee.getEmpName();
            device.setDeviceMaintainEmp(empName);
            list.add(device);
        }
        pageBean.setRows(list);
        return pageBean;
    }
}
