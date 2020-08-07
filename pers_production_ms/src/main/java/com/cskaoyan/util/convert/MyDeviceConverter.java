package com.cskaoyan.util.convert;

import com.cskaoyan.domain.Device;
import com.cskaoyan.domain.DeviceExt;
import com.cskaoyan.util.MyDevice;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class MyDeviceConverter implements MyDevice {

    @Override
    public Object convert(Object device,Object deviceExt){

        Field[] field = device.getClass().getDeclaredFields();

        for (int i = 0; i < field.length; i++) {
            String name = field[i].getName();
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            String type = field[i].getGenericType().toString();
            System.out.println(type);
            if (type.equals("class java.lang.String")) {
                try {
                    Method m = deviceExt.getClass().getMethod("set" + name, String.class);
                    Method m2 = deviceExt.getClass().getMethod("get" + name);

                    m.invoke(deviceExt, m2.invoke(device));
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            if (type.equals("class java.util.Date")) {
                try {
                    Method m = deviceExt.getClass().getMethod("set" + name, Date.class);
                    Method m2 = deviceExt.getClass().getMethod("get" + name);
                    m.invoke(deviceExt, m2.invoke(device));
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

            if (type.equals("class java.math.BigDecimal")) {
                try {
                    Method m = deviceExt.getClass().getMethod("set" + name, BigDecimal.class);
                    Method m2 = deviceExt.getClass().getMethod("get" + name);
                    m.invoke(deviceExt, m2.invoke(device));
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return deviceExt;
    }

}
