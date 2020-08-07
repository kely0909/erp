package com.cskaoyan.controller;

import com.cskaoyan.domain.*;
import com.cskaoyan.service.DeviceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @ResponseBody
    @RequestMapping("/deviceList/list")
    public PageBean getlist(Integer page, Integer rows){
        PageBean<DeviceExt> list = deviceService.list(page, rows);
        return list;
    }

    @ResponseBody
    @RequestMapping("/device/deviceList")
    public ModelAndView getdeviceListview(ModelAndView mv) {
        mv.setViewName("/deviceList");
        return mv;
    }
    @ResponseBody
    @RequestMapping("/deviceList/get/{formName}")
    public Device getByID(@PathVariable String formName) {

        Device byId = deviceService.findById(formName);
        return byId;
    }

    @RequestMapping("/deviceCheck/search_device_by_deviceTypeName")
    @ResponseBody
    public PageBean searchByName (String searchValue,Integer page, Integer rows){
        return deviceService.searchByName(searchValue, page, rows);
    }

}
