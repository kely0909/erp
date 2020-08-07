package com.cskaoyan.service;

import com.cskaoyan.domain.DeviceCheckExt;
import com.cskaoyan.domain.DeviceExt;
import com.cskaoyan.domain.PageBean;
import com.cskaoyan.domain.Process;


public interface DeviceCheckService {

    PageBean<DeviceCheckExt> list(Integer page, Integer rows);


    PageBean<DeviceCheckExt> searchbydeviceCheckId(String searchValue, Integer page, Integer rows);

    PageBean<DeviceCheckExt> searchByName(String searchValue, Integer page, Integer rows);
}


