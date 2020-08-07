package com.cskaoyan.controller;

import com.cskaoyan.domain.DeviceFault;
import com.cskaoyan.domain.PageBean;
import com.cskaoyan.service.DeviceCheckService;
import com.cskaoyan.service.DeviceFaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DeviceFaultController {
    @Autowired
    DeviceCheckService deviceCheckService;

    @Autowired
    DeviceFaultService deviceFaultService;

    @ResponseBody
    @RequestMapping("/deviceFault/list")
    public PageBean deviceCheckist(Integer page, Integer rows) {

        return deviceFaultService.list(page, rows);
    }

    @ResponseBody
    @RequestMapping("/device/deviceFault")
    public ModelAndView deviceFaultListview(ModelAndView mv) {
        mv.setViewName("/deviceFault");
        return mv;
    }

    @ResponseBody
    @RequestMapping("/deviceFault/get/{formName}")
    public DeviceFault getByID(@PathVariable String formName) {

        return deviceFaultService.selectByPrimaryKey(formName);

    }


}
