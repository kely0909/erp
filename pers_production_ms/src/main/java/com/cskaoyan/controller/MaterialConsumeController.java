package com.cskaoyan.controller;

import com.cskaoyan.domain.MaterialConsume;
import com.cskaoyan.domain.PageBean;
import com.cskaoyan.service.MaterialConsuemeService;
import org.apache.log4j.LogManager;
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

import java.util.Map;

@Controller
public class  MaterialConsumeController {

    @Autowired
    private MaterialConsuemeService materialConsuemeService;

    private Logger logger = LogManager.getLogger(MaterialConsumeController.class);

    //时间转换器
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }


    /**
     * 进入物料消耗模块
     */

    @RequestMapping(value = "materialConsume/find")
    public String materialConsumeFind() {
        logger.info("进入到materialConsume/find");
        return "materialConsume_list";
    }


    @RequestMapping(value = "materialConsume/list")
    @ResponseBody
    public PageBean<MaterialConsume> materialConsumeList(Integer page, Integer rows) {

        //从数据库中找到物料消耗信息，并返回给前端
        PageBean<MaterialConsume> list = materialConsuemeService.findList(page, rows);
        logger.info("进入到materialConsume/list--------------" + list);
        return list;
    }

    /**
     * 物料消耗模块增加
     */

    @RequestMapping(value = "materialConsume/add_judge", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> addJude(ModelAndView modelAndView) {

        logger.info("进入到materialConsume/add_judge");

        //判断
        return null;
    }


    @RequestMapping(value = "materialConsume/add")
    public String add() {
        logger.info("进到materialConsume/add");
        return "materialConsume_add";
    }

    @RequestMapping(value = "materialConsume/insert")
    @ResponseBody
    public Map<String, String> insert(@Valid MaterialConsume materialConsume, BindingResult bindingResult) {

        logger.info("进入到materialConsume/insert");
        Map<String, String> insertCheck = new HashMap<>();

        try {

            if(bindingResult.hasErrors()){

                FieldError fieldError = bindingResult.getFieldError();
                String field = fieldError.getField();
                String defaultMessage = fieldError.getDefaultMessage();

                insertCheck.put("msg", defaultMessage);
            }else {

                //插入成功返回1,插入失败返回0或其他
                if (materialConsuemeService.insert(materialConsume) == 1) {
                    insertCheck.put("status", "200");
                } else {
                    insertCheck.put("msg", "插入物料消耗失败");
                }
            }

        } catch (Exception e) {
            logger.info("material/insert异常！");
            insertCheck.put("msg", "添加物料消耗出现异常，请联系管理员");
        } finally {
            return insertCheck;
        }
    }

    /**
     *物料消耗修改
     */
    @RequestMapping(value = "materialConsume/edit_judge")
    @ResponseBody
    public Map<String, String> editJude(){
        logger.info("进入到materialConsume/edit_judge");

        //判断

        return null;
    }

    @RequestMapping(value = "materialConsume/edit")
    public String materialConsumelEdit(){
        logger.info("进入到materialConsume/edit");
        return "materialConsume_edit";
    }

    @RequestMapping(value = "materialConsume/update_all")
    @ResponseBody
    public Map<String, String> updateAll(@Valid MaterialConsume materialConsume, BindingResult bindingResult){

        logger.info("进入到materialConsume/update_all");

        Map<String, String> updateCheck = new HashMap<>();

        try{
            if(bindingResult.hasErrors()){
                FieldError fieldError = bindingResult.getFieldError();
                String field = fieldError.getField();
                String defaultMessage = fieldError.getDefaultMessage();

                updateCheck.put("msg", defaultMessage);
            }else {
                if(materialConsuemeService.update(materialConsume) == 1){
                    updateCheck.put("status", "200");
                }else{
                    updateCheck.put("msg", "修改物料消耗失败！");
                }
            }

        }catch (Exception e){
            logger.info("修改物料消耗异常");
            updateCheck.put("msg", "修改物料消耗异常，请联系管理员！");
        }

        return updateCheck;
    }


    /**
     *物料消耗删除
     */
    @RequestMapping("materialConsume/delete_judge")
    @ResponseBody
    public Map<String, String> materialConsumeDelete(){

        logger.info("进入到materialConsume/delete_judge");

        //判断
        return null;
    }

    /**
     * 根据ids批量删除
     * @param ids
     * @return
     */
    @RequestMapping("materialConsume/delete_batch")
    @ResponseBody
    public Map<String, String> deleteBatch(String ids){

        logger.info("进入到materialConsume/delete_batch");
        Map<String, String> deleteCheck = new HashMap<>();
        String[] idsString = ids.split(",");

        for (String materialConsumeId : idsString) {
            materialConsuemeService.delete(materialConsumeId);
        }

        deleteCheck.put("status", "200");
        return deleteCheck;
    }

    /**
     * 物料消耗查找
     *
     * 根据以下查找
     * 物料消耗编号 consumeId
     * 作业编号 workId
     * 物料编号 materialId
     */

    //根据物料消耗Id查找
    @RequestMapping("materialConsume/search_materialConsume_by_consumeId")
    @ResponseBody
    public PageBean<MaterialConsume> searchByConsumeId(String searchValue,Integer page, Integer rows){

        logger.info("进入到materialConsume/search_materialConsume_by_consumeId");

        return materialConsuemeService.findListByConsumeId(searchValue, page, rows);
    }

    //根据作业编号查找
    @RequestMapping("materialConsume/search_materialConsume_by_workId")
    @ResponseBody
    public PageBean<MaterialConsume> searchByWorkId(String searchValue,Integer page, Integer rows){

        logger.info("进入到materialConsume/search_materialConsume_by_workId");

        return materialConsuemeService.findListByWorkId(searchValue, page, rows);
    }

    //根据物料编号查找
    @RequestMapping("materialConsume/search_materialConsume_by_materialId")
    @ResponseBody
    public PageBean<MaterialConsume> searchByMaterialId(String searchValue,Integer page, Integer rows){

        logger.info("进入到materialConsume/search_materialConsume_by_materialId");

        return materialConsuemeService.findListByMaterialId(searchValue, page, rows);
    }






}



