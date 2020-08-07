package com.cskaoyan.controller;

import com.cskaoyan.domain.DeviceCheck;
import com.cskaoyan.domain.PageBean;
import com.cskaoyan.domain.Process;
import com.cskaoyan.service.DeviceCheckService;
import com.cskaoyan.service.DeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class DeviceCheckController {

    @Autowired
    DeviceCheckService deviceCheckService;

    /*@ResponseBody
    @RequestMapping("/deviceCheck/list")
    public PageBean deviceCheckist(Integer page, Integer rows) {

        return deviceCheckService.list(page, rows);
    }*/

    @ResponseBody
    @RequestMapping("/device/deviceCheck")
    public ModelAndView deviceCheckdeviceListview(ModelAndView mv) {
        mv.setViewName("/deviceCheck");
        return mv;
    }

    @RequestMapping("/deviceCheck/search_deviceCheck_by_deviceCheckId")
    @ResponseBody
    public PageBean searchById (String searchValue, Integer page, Integer rows){
        return deviceCheckService.searchbydeviceCheckId(searchValue, page, rows);
    }

    @RequestMapping("/deviceCheck/search_deviceCheck_by_deviceName")
    @ResponseBody
    public PageBean searchByName (String searchValue,Integer page, Integer rows){
        return deviceCheckService.searchByName(searchValue, page, rows);
    }

}
