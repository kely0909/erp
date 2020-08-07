package com.cskaoyan.controller;

import com.cskaoyan.domain.PageBean;
import com.cskaoyan.domain.Process;
import com.cskaoyan.service.ProcessService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    ProcessService processService;
    private static Logger logger = LogManager.getLogger(ProcessController.class);

    @RequestMapping("/find")
    public String find() {
        logger.info("进入/process/find");
        System.out.println("进入/process/find");
        return "process_list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageBean<Process> list(Integer page, Integer rows) {
        logger.info("进入/process/list，分页信息如下");
        logger.info("page = " + page);
        logger.info("rows = " + rows);

        return processService.list(page, rows);
    }

    @RequestMapping(value = "/add_judge", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> add_judge(HttpSession session) {
        logger.info("进入/add_judge，分页信息如下");
        HashMap<String, String> permissionCheck = new HashMap<>();
        // 权限检验 return String msg(权限不足！请登录负责人或者超级管理员账号后再进行操作)
        /*if (msg != null) {
            permissionCheck.put("msg", msg);
        }*/
        /*
            test代码片段
            if (false) {
                permissionCheck.put("msg", "test");
            }
        */
        return permissionCheck;
    }

    @RequestMapping("/add")
    public String add() {
        logger.info("权限验证通过！进入/process/add");
        return "process_add";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String>  insert (Process process){
        logger.info("/process/insert");
        Map<String, String> insertCheck = new HashMap<>();

        try {
            if (processService.insert(process) == 1) {
                insertCheck.put("status", "200");
            }
        } catch (Exception e) {
            logger.info("insert异常！", e);
            insertCheck.put("msg", "添加异常，请联系管理员！");
        } finally {
            //前端显示
            return insertCheck;
        }
    }

    @RequestMapping(value = "edit_judge",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> edit_judge(HttpSession session){
        logger.info("进入/process/edit_judge");
        HashMap<String, String> permissionCheck = new HashMap<>();

        // 权限检验 return String msg(权限不足！请登录负责人或者超级管理员账号后再进行操作)
        /*if (msg != null) {
            permissionCheck.put("msg", msg);
        }*/
        /*
            test代码片段
            if (false) {
                permissionCheck.put("msg", "test");
            }
        */
        return permissionCheck;
    }

    @RequestMapping("/edit")
    public String edit(){
        logger.info("权限验证通过！进入/technologyRequirement/edit");
        return "process_edit";
    }
    @RequestMapping(value="/update_all",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> update_all(Process process){
        logger.info("进入/technologyRequirement/update_all");
        Map<String, String> updateCheck = new HashMap<>();
        try {
            if (processService.update(process) == 1) {
                updateCheck.put("status", "200");
            }
        } catch (Exception e) {
            logger.info("insert异常！", e);
            updateCheck.put("msg", "添加异常，请联系管理员！");
        } finally {
            //前端显示
            return updateCheck;
        }
    }

    @RequestMapping(value="delete_judge",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> delete_judge(HttpSession session) {
        logger.info("进入/process/delete_judge");
        HashMap<String, String> permissionCheck = new HashMap<>();
        // 权限检验 return String msg(权限不足！请登录负责人或者超级管理员账号后再进行操作)
        /*if (msg != null) {
            permissionCheck.put("msg", msg);
        }*/
        /*
            test代码片段
            if (false) {
                permissionCheck.put("msg", "test");
            }
        */
        return permissionCheck;
    }

    @RequestMapping("delete_batch")
    @ResponseBody
    public Map<String, String> delete_batch (String ids){
        logger.info("进入/process/delete_batch");
        HashMap<String, String> deleteCheck = new HashMap<>();
        String[] ids1 = ids.split(",");

        for (int i = 0; i < ids1.length; i++) {
            processService.delete(ids1[i]);
        }
        deleteCheck.put("status", "200");
        return deleteCheck;
    }


    @RequestMapping("search_process_by_processId")
    @ResponseBody
    public PageBean<Process> searchById (String searchValue,Integer page, Integer rows){
        return processService.findProcessById(searchValue, page, rows);
    }

    @RequestMapping("search_process_by_technologyPlanId")
    @ResponseBody
    public PageBean<Process> searchByName (String searchValue,Integer page, Integer rows){
        return processService.findProcessByPlanId(searchValue, page, rows);
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public Process selectById(@PathVariable("id") String id){
        //System.out.println(id);
        return  processService.selectProcessById(id);
    }

    @RequestMapping("/get_data")
    @ResponseBody
    public List<Process> get_data(){
        return processService.selectAllProcess();
    }

}
