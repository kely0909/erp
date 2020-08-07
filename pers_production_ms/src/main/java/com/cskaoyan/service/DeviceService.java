package com.cskaoyan.service;

import com.cskaoyan.domain.Device;
import com.cskaoyan.domain.DeviceExt;
import com.cskaoyan.domain.PageBean;


public interface DeviceService {

     PageBean<DeviceExt> list(Integer page, Integer rows);

     Device findById(String id);

    PageBean<DeviceExt> searchByName(String searchValue, Integer page, Integer rows);
}

