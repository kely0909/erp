package com.cskaoyan.controller;

import com.alibaba.druid.sql.visitor.functions.Bin;
import com.cskaoyan.domain.Material;
import com.cskaoyan.domain.MaterialReceive;
import com.cskaoyan.domain.PageBean;
import com.cskaoyan.service.MaterialReceiveService;
import com.cskaoyan.service.MaterialService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;

@Controller
public class MaterialReceiveController {

    @Autowired
    MaterialReceiveService materialReceiveService;

    @Autowired
    MaterialService materialService;

    private static Logger logger = org.apache.log4j.LogManager.getLogger(MaterialController.class);

    //时间转换器
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    /**
     * 进入物料收入模块
     */

    @RequestMapping(value = "materialReceive/find")
    public String materialReceiveFind() {
        logger.info("进入到materialReceive/find");
        return "materialReceive_list";
    }

    @RequestMapping(value = "materialReceive/list")
    @ResponseBody
    public PageBean<MaterialReceive> materialReceiveList(Integer page, Integer rows) {

        //从数据库中查找物料收入信息，并返回json对象信息
        PageBean<MaterialReceive> list = materialReceiveService.findList(page, rows);
        logger.info("MaterialReceiveController--materialList--------" + list);
        return list;
    }

    //material/get/
    @RequestMapping(value = "material/get/{materialId}")
    @ResponseBody
    public Material get(@PathVariable(value = "materialId") String materialId) {

        logger.info("material/get/materialId--------" + materialId);
        Material material = materialService.findById(materialId);

        return material;
    }

    /**
     * 物料输入模块增加
     */
    @RequestMapping(value = "materialReceive/add_judge", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> addJudge(ModelAndView modelAndView) {

        logger.info("进入到materialReceive/add_judge");
        Map<String, String> permissionCheck = new HashMap<>();

        //判断权限是否足够

        return null;
    }


    @RequestMapping(value = "materialReceive/add")
    public String add() {
        logger.info("进到到materialReceive/add");
        return "materialReceive_add";
    }

    //material/get_data
    @RequestMapping(value = "material/get_data")
    @ResponseBody
    public List<Material> getData() {
        logger.info("material/get_data");
        List<Material> materials = materialService.findAllList();
        return materials;
    }


    @RequestMapping(value = "materialReceive/insert")
    @ResponseBody
    public Map<String, String> insert(@Valid MaterialReceive materialReceive, BindingResult bindingResult) {

        logger.info("materialReceive-------------" + materialReceive);

        logger.info("进入到materialReceive/insert");
        Map<String, String> insertCheck = new HashMap<>();
        logger.info("materialReceive------------------" + materialReceive);
        try {

            if (bindingResult.hasErrors()) {
                FieldError fieldError = bindingResult.getFieldError();
                String field = fieldError.getField();
                String defaultMessage = fieldError.getDefaultMessage();

                insertCheck.put("msg", defaultMessage);
            } else {
                //插入成功返回1,插入失败返回0或其他
                if (materialReceiveService.insert(materialReceive) == 1) {
                    insertCheck.put("status", "200");
                } else {
                    insertCheck.put("msg", "插入物料收入失败");
                }
            }
        } catch (Exception e) {
            logger.info("material/insert异常！");
            insertCheck.put("msg", "添加物料收入出现异常，请联系管理员");
        } finally {
            return insertCheck;
        }

    }


    /**
     * 物料收入修改
     */
    @RequestMapping(value = "materialReceive/edit_judge")
    @ResponseBody
    public Map<String, String> editJude() {
        logger.info("进入到materialReceive/edit_judge");

        //判断

        return null;
    }

    @RequestMapping(value = "materialReceive/edit")
    public String materialReceiveEdit() {
        logger.info("进入到materialReceive/edit");
        return "materialReceive_edit";
    }

    @RequestMapping(value = "materialReceive/update_all")
    @ResponseBody
    public Map<String, String> updateAll(@Valid MaterialReceive materialReceive, BindingResult bindingResult) {

        logger.info("进入到MaterialReceive/update_all");
        Map<String, String> updateCheck = new HashMap<>();

        try {

            if (bindingResult.hasErrors()) {
                FieldError fieldError = bindingResult.getFieldError();
                String field = fieldError.getField();
                String defaultMessage = fieldError.getDefaultMessage();
                updateCheck.put("msg", defaultMessage);

            } else {

                if (materialReceiveService.update(materialReceive) == 1) {
                    updateCheck.put("status", "200");
                } else {
                    updateCheck.put("msg", "修改物料收入失败！");
                }
            }

        } catch (Exception e) {
            logger.info("修改物料收入异常");
            updateCheck.put("msg", "修改物料收入异常，请联系管理员！");
        }

        return updateCheck;
    }


    /**
     * 物料详情删除
     */
    @RequestMapping("materialReceive/delete_judge")
    @ResponseBody
    public Map<String, String> materialReceiveDelete() {

        logger.info("进入到material/delete_judge");

        //判断
        return null;
    }


    /**
     * 根据ids批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("materialReceive/delete_batch")
    @ResponseBody
    public Map<String, String> deleteBatch(String ids) {

        logger.info("进入到materialReceive/delete_batch");
        Map<String, String> deleteCheck = new HashMap<>();
        String[] idsString = ids.split(",");

        for (String materialReceiveId : idsString) {
            materialReceiveService.delete(materialReceiveId);
        }

        deleteCheck.put("status", "200");
        return deleteCheck;
    }

    /**
     * 物料收入查找
     * 根据物料收入编号或物料编号
     */

    @RequestMapping("materialReceive/search_materialReceive_by_receiveId")
    @ResponseBody
    public PageBean<MaterialReceive> searchByReceiveId(String searchValue, Integer page, Integer rows) {

        logger.info("进入到materialReceive/search_materialReceive_by_receiveId");

        return materialReceiveService.findListByReceiveId(searchValue, page, rows);
    }

    @RequestMapping("materialReceive/search_materialReceive_by_materialId")
    @ResponseBody
    public PageBean<MaterialReceive> searchByMaterialId(String searchValue, Integer page, Integer rows) {

        logger.info("进入到materialReceive/search_materialReceive_by_materialId");

        return materialReceiveService.findListByMaterialId(searchValue, page, rows);
    }


}
