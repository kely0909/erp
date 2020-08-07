package com.cskaoyan.controller;

import com.cskaoyan.domain.PageBean;
import com.cskaoyan.service.DeviceCheckService;
import com.cskaoyan.service.DeviceFaultService;
import com.cskaoyan.service.DeviceMaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DeviceMaintainController {

    @Autowired
    DeviceMaintainService deviceMaintainService;

    @ResponseBody
    @RequestMapping("/deviceMaintain/list")
    public PageBean deviceCheckist(Integer page, Integer rows) {

        return deviceMaintainService.list(page, rows);
    }

    @ResponseBody
    @RequestMapping("/device/deviceMaintain")
    public ModelAndView deviceFaultListview(ModelAndView mv) {
        mv.setViewName("/deviceMaintain");
        return mv;
    }
}
