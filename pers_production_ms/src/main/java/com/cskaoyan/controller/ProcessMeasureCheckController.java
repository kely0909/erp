package com.cskaoyan.controller;

import com.cskaoyan.domain.ProcessMeasureCheck;
import com.cskaoyan.domain.UnqualifyApply;
import com.cskaoyan.domain.objUtils.CustomResult;
import com.cskaoyan.domain.objUtils.DataGridResult;
import com.cskaoyan.service.ProcessMeasureCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = {"/p_measure_check","/pMeasureCheck"})
public class ProcessMeasureCheckController {

    @Autowired
    ProcessMeasureCheckService processMeasureCheckService;

    /*-----------------获得背景视图页面-------------------*/
    @RequestMapping("/find")
    public String find(){
        return "p_measure_check_list";
    }

    @RequestMapping("/add")
    public String addPage(){
        return "p_measure_check_add";
    }

    @RequestMapping("/edit")
    public String editPage(){
        return "p_measure_check_edit";
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
        DataGridResult result = processMeasureCheckService.findWithPageAndRows(page, rows);
        return result;
    }

    @RequestMapping("/search_pMeasureCheck_by_pMeasureCheckId")
    @ResponseBody
    public DataGridResult searchUnqualifyByUnqualifyId(Integer page, Integer rows,
                                                       @RequestParam(value = "searchValue") String searchVagueId){
        DataGridResult result = processMeasureCheckService.vagueSearchById(page, rows, searchVagueId);
        return result;
    }

    @RequestMapping("/delete_batch")
    @ResponseBody
    public CustomResult deleteByIds(String[] ids){
        CustomResult customResult = processMeasureCheckService.deleteBatch(ids);

        return customResult;
    }

    @RequestMapping(value = "/update_all")
    @ResponseBody
    public CustomResult update(ProcessMeasureCheck processMeasureCheck){
        CustomResult customResult = processMeasureCheckService.update(processMeasureCheck);

        return customResult;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public CustomResult add(ProcessMeasureCheck processMeasureCheck){
        CustomResult customResult = processMeasureCheckService.add(processMeasureCheck);

        return customResult;
    }

}
