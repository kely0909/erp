package com.cskaoyan.controller;

import com.cskaoyan.domain.UnqualifyApply;
import com.cskaoyan.domain.objUtils.CustomResult;
import com.cskaoyan.domain.objUtils.DataGridResult;
import com.cskaoyan.service.UnqualifyApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/unqualify")
public class UnqualifyApplyController {

    @Autowired
    UnqualifyApplyService unqualifyApplyService;



    /*-----------------获得背景视图页面-------------------*/
    @RequestMapping("/find")
    public String find() {

        return "/unqualify_list";
    }

    @RequestMapping("/edit")
    public String editPage(){
        return "/unqualify_edit";
    }


    @RequestMapping("/add")
    public String addPage(){
        return "/unqualify_add";
    }



    /*---------------------权限验证--------------------*/
    @RequestMapping("/add_judge")
    @ResponseBody
    public CustomResult addUnqualifyApplyJudge(){

        //先跳过验证
        CustomResult customResult = new CustomResult();
        customResult.setMsg(null);
        return customResult;
    }

    @RequestMapping("/edit_judge")
    @ResponseBody
    public CustomResult editUnqualifyJudge(){
        //先跳过验证
        CustomResult customResult = new CustomResult();
        customResult.setMsg(null);
        return customResult;
    }



    @RequestMapping("/delete_judge")
    @ResponseBody
    public CustomResult deleteUnqualifyJudge() throws Exception{

//        Map<String,Object> map = new HashMap<String,Object>();
////        map.put("msg", "这是个什么?");
//        //msg添加代表删除权限不够，没有就直接删除
//        return map;

        CustomResult customResult = new CustomResult();
        customResult.setMsg(null);
        return customResult;
    }




    /*------------------增删改查(CRUD)------------------------*/
    @RequestMapping("/list")
    @ResponseBody
    public DataGridResult getItemList(Integer page, Integer rows) throws Exception{
        DataGridResult result = unqualifyApplyService.findWithPageAndRows(page, rows);
        return result;
    }

    @RequestMapping("/search_unqualify_by_unqualifyId")
    @ResponseBody
    public DataGridResult searchUnqualifyByUnqualifyId(Integer page, Integer rows,
                                                       @RequestParam(value = "searchValue") String vagueUnqualifyId){
        DataGridResult result = unqualifyApplyService.vagueSearchById(page, rows, vagueUnqualifyId);
        return result;
    }


    @RequestMapping("/search_unqualify_by_productName")
    @ResponseBody
    public DataGridResult searchUnqualifyByProductName(Integer page, Integer rows,
                                                       @RequestParam(value = "searchValue") String vagueUnqualifyName){
        DataGridResult result = unqualifyApplyService.vagueSearchByName(page, rows, vagueUnqualifyName);
        return  result;
    }




    @RequestMapping("/delete_batch")
    @ResponseBody
    public CustomResult deleteByIds(String[] ids){
        CustomResult customResult = unqualifyApplyService.deleteBatch(ids);

        //删除失败后前端没反映
        //CustomResult customResult = null;

        return customResult;
    }

    @RequestMapping(value = "/update_all")
    @ResponseBody
    public CustomResult update(UnqualifyApply unqualifyApply){
        CustomResult customResult = unqualifyApplyService.update(unqualifyApply);

        return customResult;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public CustomResult add(UnqualifyApply unqualifyApply){
        CustomResult customResult = unqualifyApplyService.add(unqualifyApply);

        return customResult;
    }





}
