package com.cskaoyan.service.impl;

import com.cskaoyan.dao.DeviceCheckMapper;
import com.cskaoyan.dao.DeviceMapper;
import com.cskaoyan.dao.DeviceTypeMapper;
import com.cskaoyan.dao.EmployeeMapper;
import com.cskaoyan.domain.*;
import com.cskaoyan.service.DeviceService;
import com.cskaoyan.util.convert.MyDateConverter;
import com.cskaoyan.util.convert.MyDeviceConverter;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
@Service
public class DeviceServiceImpl implements DeviceService {

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
    public PageBean<DeviceExt> list(Integer page, Integer rows) {
        PageHelper.startPage(page, rows, true);
        List<Device> devices = deviceMapper.selectALL();

        PageBean pageBean = new PageBean<>(devices);

        List<DeviceExt> list = new ArrayList<>();
        for (Device device : devices) {
            DeviceExt convert =new DeviceExt();
             convert = (DeviceExt)myDeviceConverter.convert(device,convert);

            String deviceTypeId = device.getDeviceTypeId();
            if (deviceTypeId!=null)
            {
            DeviceType deviceType = deviceTypeMapper.selectByPrimaryKey(deviceTypeId);
                Employee employee = employeeMapper.selectByPrimaryKey(device.getDeviceKeeperId());

                String deviceTypeName = deviceType.getDeviceTypeName();
                String empName = employee.getEmpName();
                convert.setDeviceKeeper(empName);
                convert.setDeviceTypeName(deviceTypeName);
            }
            list.add(convert);
        }
        pageBean.setRows(list);
        return  pageBean;
    }

    @Override
    public Device findById(String id) {
        return deviceMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageBean<DeviceExt> searchByName(String searchValue, Integer page, Integer rows) {

        searchValue = "%" + searchValue + "%";
        PageHelper.startPage(page, rows, true);

        List<DeviceCheck> deviceChecks = deviceMapper.searchByName(searchValue);

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
