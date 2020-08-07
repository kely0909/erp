package com.cskaoyan.domain;

public class DeviceCheckExt extends DeviceCheck{

    private String deviceName;
    private String deviceCheckEmp;

    public String getDeviceCheckEmp() {
        return deviceCheckEmp;
    }

    public void setDeviceCheckEmp(String deviceCheckEmp) {
        this.deviceCheckEmp = deviceCheckEmp;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public String toString() {
        String s = super.toString();
        return s+"DeviceCheckExt{" +
                "deviceName='" + deviceName + '\'' +
                ", deviceCheckEmp='" + deviceCheckEmp + '\'' +
                '}';
    }
}
