package com.cskaoyan.controller;

import com.cskaoyan.domain.FinalCountCheck;
import com.cskaoyan.domain.objUtils.CustomResult;
import com.cskaoyan.domain.objUtils.DataGridResult;
import com.cskaoyan.service.FinalCountCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = {"/f_count_check", "/fCountCheck"})
public class FinalCountCheckController {
    
    @Autowired
    FinalCountCheckService finalCountCheckService;

    /*-----------------获得背景视图页面-------------------*/
    @RequestMapping("/find")
    public String find(){
        return "f_count_check_list";
    }

    @RequestMapping("/add")
    public String addPage(){
        return "f_count_check_add";
    }

    @RequestMapping("/edit")
    public String editPage(){
        return "f_count_check_edit";
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
        DataGridResult result = finalCountCheckService.findWithPageAndRows(page, rows);
        return result;
    }

    @RequestMapping("/search_fCountCheck_by_fCountCheckId")
    @ResponseBody
    public DataGridResult searchCountCheckByCountCheckId(Integer page, Integer rows,
                                                       @RequestParam(value = "searchValue") String searchVagueId){
        DataGridResult result = finalCountCheckService.vagueSearchByCountCheckId(page, rows, searchVagueId);
        return result;
    }
    //search_fCountCheck_by_orderId
    @RequestMapping("/search_fCountCheck_by_orderId")
    @ResponseBody
    public DataGridResult searchCountCheckByCountOrderId(Integer page, Integer rows,
                                                         @RequestParam(value = "searchValue") String searchVagueId){
        DataGridResult result = finalCountCheckService.vagueSearchByOrderId(page, rows, searchVagueId);
        return result;
    }

    @RequestMapping("/delete_batch")
    @ResponseBody
    public CustomResult deleteByIds(String[] ids){
        CustomResult customResult = finalCountCheckService.deleteBatch(ids);

        return customResult;
    }

    @RequestMapping(value = "/update_all")
    @ResponseBody
    public CustomResult update(FinalCountCheck finalCountCheck){
        CustomResult customResult = finalCountCheckService.update(finalCountCheck);

        return customResult;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public CustomResult add(FinalCountCheck finalCountCheck){
        CustomResult customResult = finalCountCheckService.add(finalCountCheck);

        return customResult;
    }
}
