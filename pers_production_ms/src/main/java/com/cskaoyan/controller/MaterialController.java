package com.cskaoyan.controller;

import com.cskaoyan.domain.Material;
import com.cskaoyan.domain.PageBean;
import com.cskaoyan.service.MaterialService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    private static Logger logger = LogManager.getLogger(MaterialController.class);

    /**
     * 进入物料详情模块
     */
    @RequestMapping(value = "material/find")
    public String materialFind() {
        logger.info("进入到material/find");
        return "material_list";
    }

    @RequestMapping(value = "material/list")
    @ResponseBody
    public PageBean<Material> materialList(Integer page, Integer rows) {

        //从数据库中查找物料信息，并返回json对象信息
        logger.info("page = " + page);
        logger.info("rows = " + rows);
        PageBean<Material> list = materialService.findList(page, rows);
        logger.info("materialList--------" + list);
        return list;
    }

    /**
     * 物料详情增加
     */
    @RequestMapping(value = "material/add_judge", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> addJudge(ModelAndView modelAndView) {

        logger.info("进入到material/add_judge");
        Map<String, String> permissionCheck = new HashMap<>();

        //判断权限是否足够

        return null;
    }

    //add写法1
    /*@RequestMapping(value = "material/add")
    @ResponseBody
    public ModelAndView add(ModelAndView modelAndView){

        logger.info("进入到material/add");
        modelAndView.setViewName("material_add");
        return modelAndView;
    }*/
    //写法2
    @RequestMapping(value = "material/add")
    public String add() {
        logger.info("进到到material/add");
        return "material_add";
    }

    @RequestMapping(value = "material/insert")
    @ResponseBody
    public Map<String, String> insert(@Valid Material material, BindingResult bindingResult) {

        logger.info("进入到material/insert");
        Map<String, String> insertCheck = new HashMap<>();

        try {

            if (bindingResult.hasErrors()) {

                FieldError fieldError = bindingResult.getFieldError();
                String field = fieldError.getField();
                String defaultMessage = fieldError.getDefaultMessage();

                logger.info("field---------------" + field);
                logger.info("defaultMessage---------------" + defaultMessage);
                insertCheck.put("msg", defaultMessage);

            } else {
                //插入成功返回1,插入失败返回0或其他
                if (materialService.insert(material) == 1) {
                    insertCheck.put("status", "200");
                } else {
                    insertCheck.put("msg", "插入物料详情失败");
                }
            }

        } catch (Exception e) {
            logger.info("material/insert异常！");
            insertCheck.put("msg", "添加物料详情出现异常，请联系管理员");
        } finally {
            return insertCheck;
        }


    }

    /**
     * 物料详情修改
     */
    //涉及json，需要用ResponseBody返回数据
    @RequestMapping(value = "material/edit_judge")
    @ResponseBody
    public Map<String, String> editJude() {

        logger.info("进入到material/edit_judge");
        return null;
    }


    @RequestMapping(value = "material/edit")
    public String materialEdit() {
        logger.info("进入到material/edit");
        return "material_edit";
    }

    //material/update_all
    @RequestMapping(value = "material/update_all")
    @ResponseBody
    public Map<String, String> updateAll(@Valid Material material, BindingResult bindingResult) {

        logger.info("进入到material/update_all");
        Map<String, String> updateCheck = new HashMap<>();
        try {

            if(bindingResult.hasErrors()){
                FieldError fieldError = bindingResult.getFieldError();
                String field = fieldError.getField();
                String defaultMessage = fieldError.getDefaultMessage();

                updateCheck.put("msg", defaultMessage);

            }else{
                if (materialService.update(material) == 1) {
                    updateCheck.put("status", "200");
                } else {
                    updateCheck.put("msg", "修改物料详情失败！");
                }
            }

        } catch (Exception e) {
            logger.info("修改物料详情异常");
            updateCheck.put("msg", "修改物料异常，请联系管理员！");
        }
        return updateCheck;
    }


    /**
     * 物料详情删除
     */

    @RequestMapping("material/delete_judge")
    @ResponseBody
    public Map<String, String> materialDelete() {

        logger.info("进入到material/delete_judge");
        //判断是否具有删除权限

        return null;
    }

    /**
     * 根据ids批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("material/delete_batch")
    @ResponseBody
    public Map<String, String> deleteBatch(String ids) {

        logger.info("进入到material/delete_batch");
        Map<String, String> deleteCheck = new HashMap<>();
        String[] idsString = ids.split(",");

        for (String materialId : idsString) {
            materialService.delete(materialId);
        }

        deleteCheck.put("status", "200");
        return deleteCheck;
    }


    /**
     * 物料查找
     * 根据物料编号或物料类型
     */

    //material/search_material_by_materialId
    @RequestMapping("material/search_material_by_materialId")
    @ResponseBody
    public PageBean<Material> searchByMaterialId(String searchValue, Integer page, Integer rows) {

        logger.info("进入到material/search_material_by_materialId");

        return materialService.findListByMaterialId(searchValue, page, rows);
    }


    //material/search_material_by_materialType
    @RequestMapping("material/search_material_by_materialType")
    @ResponseBody
    public PageBean<Material> searchByMaterialType(String searchValue, Integer page, Integer rows) {

        logger.info("进入到material/search_material_by_materialType");

        return materialService.findListByMaterialType(searchValue, page, rows);
    }


}
