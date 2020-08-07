package com.cskaoyan.controller;

import com.cskaoyan.domain.FinalMeasuretCheck;
import com.cskaoyan.domain.objUtils.CustomResult;
import com.cskaoyan.domain.objUtils.DataGridResult;
import com.cskaoyan.service.FinalMeasuretCheckService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = {"/measure", "/fMeasureCheck"})
public class FinalMeasuretCheckController {

    @Autowired
    private FinalMeasuretCheckService finalMeasuretCheckService;

    /*-----------------获得背景视图页面-------------------*/
    @RequestMapping("/find")
    public String find(){
        return "measurement_list";
    }

    @RequestMapping("/add")
    public String addPage(){
        return "measurement_add";
    }

    @RequestMapping("/edit")
    public String editPage(){
        return "measurement_edit";
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
        DataGridResult result = finalMeasuretCheckService.findWithPageAndRows(page, rows);
        return result;
    }

    //search_fMeasureCheck_by_fMeasureCheckId
    //search_fMeasureCheck_by_fMeasureCheckId
    @RequestMapping("/search_fMeasureCheck_by_fMeasureCheckId")
    @ResponseBody
    public DataGridResult searchMeasuretChecksByMeasuretCheckId(Integer page, Integer rows,
                                                       @RequestParam(value = "searchValue") String searchVagueId){
        DataGridResult result = finalMeasuretCheckService.vagueSearchById(page, rows, searchVagueId);
        return result;
    }
    //search_fMeasureCheck_by_orderId
    @RequestMapping("/search_fMeasureCheck_by_orderId")
    @ResponseBody
    public DataGridResult searchMeasuretChecksByOrderId(Integer page, Integer rows,
                                                       @RequestParam(value = "searchValue") String searchVagueId){
        DataGridResult result = finalMeasuretCheckService.vagueSearchByOrderId(page, rows, searchVagueId);
        return result;
    }

    @RequestMapping("/delete_batch")
    @ResponseBody
    public CustomResult deleteByIds(String[] ids){
        CustomResult customResult = finalMeasuretCheckService.deleteBatch(ids);

        return customResult;
    }

    @RequestMapping(value = "/update_all")
    @ResponseBody
    public CustomResult update(FinalMeasuretCheck finalMeasuretCheck){
        CustomResult customResult = finalMeasuretCheckService.update(finalMeasuretCheck);

        return customResult;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public CustomResult add(FinalMeasuretCheck finalMeasuretCheck){
        CustomResult customResult = finalMeasuretCheckService.add(finalMeasuretCheck);

        return customResult;
    }


}
