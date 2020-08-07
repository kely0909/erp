package com.cskaoyan.service;

import com.cskaoyan.domain.DeviceMaintain;
import com.cskaoyan.domain.PageBean;

public interface DeviceMaintainService {
    PageBean<DeviceMaintain> list(Integer page, Integer rows);

}
