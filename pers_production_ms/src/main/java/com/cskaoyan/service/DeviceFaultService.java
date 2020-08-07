package com.cskaoyan.service;

import com.cskaoyan.domain.Device;
import com.cskaoyan.domain.DeviceFault;
import com.cskaoyan.domain.DeviceFaultExt;
import com.cskaoyan.domain.PageBean;

public interface DeviceFaultService {
    PageBean<DeviceFaultExt> list(Integer page, Integer rows);

    DeviceFault selectByPrimaryKey(String key);



}
