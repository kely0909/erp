package com.cskaoyan.controller;

import com.cskaoyan.domain.ProcessCountCheck;
import com.cskaoyan.domain.objUtils.CustomResult;
import com.cskaoyan.domain.objUtils.DataGridResult;
import com.cskaoyan.service.ProcessCountCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = {"/p_count_check", "/pCountCheck"})
public class ProcessCountCheckController {


    @Autowired
    ProcessCountCheckService processCountCheckService;

    /*-----------------获得背景视图页面-------------------*/
    @RequestMapping("/find")
    public String find(){
        return "p_count_check_list";
    }

    @RequestMapping("/add")
    public String addPage(){
        return "p_count_check_add";
    }

    @RequestMapping("/edit")
    public String editPage(){
        return "p_count_check_edit";
    }

    /*---------------------权限验证--------------------*/

    @RequestMapping("/add_judge")
    @ResponseBody
    public CustomResult addJudge(){
        //先跳过验证
        CustomResult customResult = new CustomResult();
        customResult.setMsg(null);
        return customResult;
    }

    @RequestMapping("/delete_judge")
    @ResponseBody
    public CustomResult deleteJudge(){
        //先跳过验证
        CustomResult customResult = new CustomResult();
        customResult.setMsg(null);
        return customResult;
    }

    @RequestMapping("/edit_judge")
    @ResponseBody
    public CustomResult editJudge(){
        //先跳过验证
        CustomResult customResult = new CustomResult();
        customResult.setMsg(null);
        return customResult;
    }


    /*---------------------增删改查(CRUD)----------------------*/

    @RequestMapping("/list")
    @ResponseBody
    public DataGridResult getItemList(Integer page, Integer rows) throws Exception{
        DataGridResult result = processCountCheckService.findWithPageAndRows(page, rows);
        return result;
    }

    @RequestMapping("/search_pCountCheck_by_pCountCheckId")
    @ResponseBody
    public DataGridResult searchCountCheckByCountCheckId(Integer page, Integer rows,
                                                       @RequestParam(value = "searchValue") String searchVagueId){
        DataGridResult result = processCountCheckService.vagueSearchById(page, rows, searchVagueId);
        return result;
    }

    @RequestMapping("/delete_batch")
    @ResponseBody
    public CustomResult deleteByIds(String[] ids){
        CustomResult customResult = processCountCheckService.deleteBatch(ids);

        return customResult;
    }

    @RequestMapping(value = "/update_all")
    @ResponseBody
    public CustomResult update(ProcessCountCheck processMeasureCheck){
        CustomResult customResult = processCountCheckService.update(processMeasureCheck);

        return customResult;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public CustomResult add(ProcessCountCheck processMeasureCheck){
        CustomResult customResult = processCountCheckService.add(processMeasureCheck);

        return customResult;
    }
}
