package com.cskaoyan.service.impl;

import com.cskaoyan.dao.DeviceCheckMapper;
import com.cskaoyan.dao.DeviceMapper;
import com.cskaoyan.dao.DeviceTypeMapper;
import com.cskaoyan.dao.EmployeeMapper;
import com.cskaoyan.domain.*;
import com.cskaoyan.service.DeviceCheckService;
import com.cskaoyan.util.convert.MyDeviceConverter;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceCheckServiceImpl implements DeviceCheckService {

    @Autowired
    DeviceTypeMapper deviceTypeMapper;

    @Autowired
    DeviceMapper deviceMapper;

    @Autowired
    DeviceCheckMapper deviceCheckMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    MyDeviceConverter myDeviceConverter;



    @Override
    public PageBean<DeviceCheckExt> list(Integer page, Integer rows) {
        PageHelper.startPage(page, rows, true);
        List<DeviceCheck> deviceChecks = deviceCheckMapper.selectALL();

        PageBean pageBean = new PageBean<>(deviceChecks);

        List<DeviceCheckExt> list = getDeviceCheckExts(deviceChecks);
        pageBean.setRows(list);
        return  pageBean;
    }

    @Override
    public PageBean<DeviceCheckExt> searchbydeviceCheckId(String searchValue, Integer page, Integer rows) {
        PageHelper.startPage(page, rows, true);
        searchValue = "%" + searchValue + "%";

        List<DeviceCheck> deviceChecks = deviceCheckMapper.findbydeviceCheckId(searchValue);

        PageBean pageBean = new PageBean<>(deviceChecks);
        List<DeviceCheckExt> list = getDeviceCheckExts(deviceChecks);
        pageBean.setRows(list);
        return  pageBean;
    }

    @Override
    public PageBean<DeviceCheckExt> searchByName(String searchValue, Integer page, Integer rows) {
        PageHelper.startPage(page, rows, true);
        searchValue = "%" + searchValue + "%";

        List<DeviceCheck> deviceChecks = deviceCheckMapper.searchByName(searchValue);

        PageBean pageBean = new PageBean<>(deviceChecks);
        List<DeviceCheckExt> list = getDeviceCheckExts(deviceChecks);
        pageBean.setRows(list);
        return  pageBean;



    }


    private List<DeviceCheckExt> getDeviceCheckExts(List<DeviceCheck> deviceChecks) {
        List<DeviceCheckExt> list = new ArrayList<>();
        for (DeviceCheck device : deviceChecks) {
            DeviceCheckExt convert=new DeviceCheckExt();
             convert =(DeviceCheckExt) myDeviceConverter.convert(device,convert);

            String deviceId = device.getDeviceId();
            if (deviceId!=null)
            {
                Device device1 = deviceMapper.selectByPrimaryKey(deviceId);
                Employee employee = employeeMapper.selectByPrimaryKey(device.getDeviceCheckEmpId());
                String empName = employee.getEmpName();
                String deviceTypeName = device1.getDeviceName();
                convert.setDeviceName(deviceTypeName);
                convert.setDeviceCheckEmp(empName);
            }
            list.add(convert);
        }
        return list;
    }


}
