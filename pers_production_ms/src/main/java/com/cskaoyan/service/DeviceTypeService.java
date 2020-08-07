package com.cskaoyan.service;

import com.cskaoyan.domain.Device;
import com.cskaoyan.domain.DeviceCheck;
import com.cskaoyan.domain.DeviceType;

import java.util.List;

public interface DeviceTypeService {
    List<DeviceType> Typeslist(Integer page, Integer rows);

    int Devicetypeinsert(DeviceType deviceType);
    int Devicetypeupdate(DeviceType deviceType);
    int Devicetypedelete(String[] ids);

    DeviceType findDeviceTypebyid(String id);

}
