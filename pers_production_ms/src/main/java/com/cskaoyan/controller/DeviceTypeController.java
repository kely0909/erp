package com.cskaoyan.controller;

import com.cskaoyan.domain.Device;
import com.cskaoyan.domain.DeviceCheck;
import com.cskaoyan.domain.DeviceType;
import com.cskaoyan.service.DeviceTypeService;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class DeviceTypeController {

    @Autowired
    DeviceTypeService deviceTypeService;
    private static Logger logger = LogManager.getLogger(DeviceTypeController.class);


    @RequestMapping("/deviceType/list")
    @ResponseBody
    public List<DeviceType> list(Integer page, Integer rows) {

        List<DeviceType> deviceTypes = deviceTypeService.Typeslist(page, rows);
        System.out.println(deviceTypes);
        return deviceTypes;
    }

    @ResponseBody
    @RequestMapping("/device/deviceType")
    public ModelAndView getdeviceTypeview(ModelAndView mv) {
        mv.setViewName("/deviceType");
        return mv;
    }

    @ResponseBody
    @RequestMapping("/deviceType/add_judge")
    public String deviceType_add_judge() {
        return null;
    }

    @ResponseBody
    @RequestMapping("/deviceType/add")
    public ModelAndView getaddview(ModelAndView mv) {

        mv.setViewName("/deviceType_add");
        return mv;
    }


    @ResponseBody
    @RequestMapping("/deviceType/insert")
    public Map<String, String> addDeviceType(DeviceType deviceType, HttpServletResponse response) throws IOException {
        System.out.println(deviceType);
        Map<String, String> insertCheck = new HashMap<>();

        try {

            int insert = deviceTypeService.Devicetypeinsert(deviceType);
            System.out.println(insert);

            if (insert == 1) {
                insertCheck.put("status", "200");
            } else {
                // i == 0
                insertCheck.put("msg", "插入不成功,清重试！");
            }
        }catch (Exception e) {
            logger.error("insert异常！", e);
            insertCheck.put("msg", "添加异常，请检查数据是否准确！");
        } finally {
            // 前端一定显示
            return insertCheck;
        }
    }


    @ResponseBody
    @RequestMapping("/deviceType/edit_judge")
    public String deviceType_edit_judge() {
        return null;
    }

    @ResponseBody
    @RequestMapping("/deviceType/edit")
    public ModelAndView geteditview(ModelAndView mv) {
        mv.setViewName("/deviceType_edit");
        return mv;
    }

    @ResponseBody
    @RequestMapping("/deviceType/update")
    public Map<String, String> editDeviceType(DeviceType deviceType, HttpServletResponse response) throws IOException {

        Map<String, String> insertCheck = new HashMap<>();
        try {

            int update = deviceTypeService.Devicetypeupdate(deviceType);
            System.out.println(update);

            if (update == 1) {
                insertCheck.put("status", "200");
            } else {
                // i == 0
                insertCheck.put("msg", "更新不成功,清重试！");
            }
        }catch (Exception e) {
            logger.error("update异常！", e);
            insertCheck.put("msg", "更新异常，请检查数据是否准确！");
        } finally {
            // 前端一定显示
            return insertCheck;
        }

    }

    @ResponseBody
    @RequestMapping("/deviceType/delete_judge")
    public String deviceType_delete_judge() {
        return null;
    }

    @ResponseBody
    @RequestMapping("/deviceType/delete_batch")
    public Map<String, String> deviceType_delete_batch(String[] ids) {
        Map<String, String> insertCheck = new HashMap<>();
        try {

            int delete = deviceTypeService.Devicetypedelete(ids);
            System.out.println(delete);

            if (delete == 1) {
                insertCheck.put("status", "200");
            } else {
                // i == 0
                insertCheck.put("msg", "删除不成功,清重试！");
            }
        }catch (Exception e) {
            logger.error("delete异常！", e);
            insertCheck.put("msg", "删除异常，请检查数据是否准确！");
        } finally {
            // 前端一定显示
            return insertCheck;
        }
    }

    @ResponseBody
    @RequestMapping("/deviceType/get/{formName}")
    public DeviceType getByID(@PathVariable String formName) {

        DeviceType deviceTypebyid = deviceTypeService.findDeviceTypebyid(formName);
        return deviceTypebyid;
    }


}
