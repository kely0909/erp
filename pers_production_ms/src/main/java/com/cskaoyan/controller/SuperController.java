//package com.cskaoyan.controller;
//
//import com.cskaoyan.domain.PageBean;
//import com.cskaoyan.domain.Product;
//import com.cskaoyan.service.SuperService;
//import com.cskaoyan.util.check.ValidityCheck;
//import com.cskaoyan.util.mapping.ParseDomainConfigFile;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import javax.validation.ConstraintViolation;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * 巩固http://elim.iteye.com/blog/2409316
// * RequestMapping通配符和路径变量越少越精确
// *
// * @author Chen Xiaoyu
// * @author Sayen Cool
// */
//@Controller
//@RequestMapping("/{manager}")
//public class SuperController {
//
//    @Autowired
//    SuperService superService;
//
//    private static Logger logger = LogManager.getLogger(SuperController.class);
//
//    @RequestMapping({"/list{name}", "/search_{manager}_by_{name}"})
//    @ResponseBody
//    public PageBean list(@PathVariable String manager, @PathVariable String name, String searchValue, Integer page, Integer rows) {
//        PageBean pageBean = null;
//        String domain = ParseDomainConfigFile.getDomain(manager);
//        try {
////            Product product = new Product();
//            domain = "product";
//            pageBean = superService.findBySearchCondition(domain, name, searchValue, page, rows);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return pageBean;
//    }
//
//    @RequestMapping(value = "/insert", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, String> insert(@PathVariable String manager, HttpServletRequest request) {
//        String domain = ParseDomainConfigFile.getDomain(manager);
//        logger.info("进入/" + domain + "/insert");
//        Map<String, String> insertCheck = new HashMap<>();
//
//        String className = "com.cskaoyan.domain." + domain.substring(0, 1).toUpperCase() + domain.substring(1);
//
//        try {
//            Object object = getInstance(className, request);
//            logger.info("自定义封装：" + object);
//
//            Set<ConstraintViolation<Object>> validate = ValidityCheck.validate(object);
//            if (validate.size() > 0) {
//                insertCheck.put("msg", String.format("参数校验失败:%s", validate.iterator().next().getMessage()));
//            } else {
//                if (superService.insert(domain, object) == 1) {
//                    insertCheck.put("status", "200");
//                } else {
//                    // i == 0
//                    insertCheck.put("msg", "插入不成功！");
//                }
//            }
//        } catch (Exception e) {
//            logger.error("insert异常！", e);
//            insertCheck.put("msg", "添加异常，请联系管理员！");
//        } finally {
//            // 前端一定显示
//            return insertCheck;
//        }
//    }
//
//    @RequestMapping(value = "/delete_batch", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, String> delete_batch(@PathVariable String manager, String[] ids) {
//        String domain = ParseDomainConfigFile.getDomain(manager);
//        logger.info("进入/" + domain + "/delete_batch");
//        Map<String, String> deleteCheck = new HashMap<>();
//
//        try {
//            if (superService.deleteByPrimaryKeyS(domain, ids) >= 1) {
//                deleteCheck.put("status", "200");
//            } else {
//                // i == 0
////                insertCheck.put("msg", msg);
//                deleteCheck.put("msg", "删除不成功！");
//            }
//        } catch (Exception e) {
//            logger.error("delete异常！", e);
//            deleteCheck.put("msg", "删除异常，请联系管理员！");
//        } finally {
//            // 前端一定显示
//            return deleteCheck;
//        }
//    }
//
//    @RequestMapping(value = "/update_all", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, String> update_all(@PathVariable String manager, HttpServletRequest request) {
//        String domain = ParseDomainConfigFile.getDomain(manager);
//        logger.info("进入/" + domain + "/update_all");
//        Map<String, String> updateCheck = new HashMap<>();
//
//        String className = "com.cskaoyan.domain." + domain.substring(0, 1).toUpperCase() + domain.substring(1);
//
//        try {
//            Object object = getInstance(className, request);
//            logger.info("自定义封装：" + object);
//
//            Set<ConstraintViolation<Object>> validate = ValidityCheck.validate(object);
//            if (validate.size() > 0) {
//                updateCheck.put("msg", String.format("参数校验失败:%s", validate.iterator().next().getMessage()));
//            } else {
//                if (superService.updateByPrimaryKey(domain, object) == 1) {
//                    updateCheck.put("status", "200");
//                    updateCheck.put("msg", "ok");
//                } else {
//                    // i == 0
////                insertCheck.put("msg", msg);
//                    updateCheck.put("msg", "更新不成功！");
//                }
//            }
//        } catch (Exception e) {
//            logger.error("update异常！", e);
//            updateCheck.put("msg", "更新异常，请联系管理员！");
//        } finally {
//            // 前端一定显示
//            return updateCheck;
//        }
//    }
//
//    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public Object getId(@PathVariable String manager, @PathVariable String id) {
//        String domain = ParseDomainConfigFile.getDomain(manager);
//        Object byId = null;
//        try {
//            byId = superService.selectByPrimaryKey(domain, id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return byId;
//    }
//
//    @RequestMapping(value = "/get_data", method = RequestMethod.POST)
//    @ResponseBody
//    public Object getData(@PathVariable String manager) {
//        String domain = ParseDomainConfigFile.getDomain(manager);
//        Object byId = null;
//        try {
//            byId = superService.findAll(domain);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return byId;
//    }
//
//    /*@RequestMapping(value = "/{operation}")
//    public String edit(@PathVariable String operation, Custom custom) {
//        logger.info("进入/custom/" + operation);
//
//        if ("find".equals(operation)) {
//            operation = "list";
//        }
//
//        return "custom_" + operation;
//    }*/
//
//    @RequestMapping(value = "/find")
//    public String list(@PathVariable String manager) {
//        logger.info("进入/" + manager + "/list");
//        return manager + "_list";
//    }
//
//    @RequestMapping(value = "/add")
//    public String add(@PathVariable String manager) {
//        logger.info("进入/" + manager + "/add");
//        return manager + "_add";
//    }
//
//    @RequestMapping(value = "/edit")
//    public String edit(@PathVariable String manager) {
//        logger.info("进入/" + manager + "/edit");
//        return manager + "_edit";
//    }
//
//    @RequestMapping(value = "/role")
//    public String role(@PathVariable String manager) {
//        logger.info("进入/" + manager + "/edit");
//        return manager + "_edit";
//    }
//
//    @RequestMapping(value = "/permission")
//    public String permission(@PathVariable String manager) {
//        logger.info("进入/" + manager + "/permission");
//        return manager + "_permission";
//    }
//
//    @RequestMapping(value = "/role_edit")
//    public String role_edit(@PathVariable String manager) {
//        logger.info("进入/" + manager + "/role_edit");
//        return manager + "_role_edit";
//    }
//
//    /**
//     * 权限控制
//     *
//     * @param operation 操作
//     * @param session   账户权限提取
//     * @return Map 返回前端的信息
//     */
//    @RequestMapping(value = "/{operation}_judge", method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String, String> judge(@PathVariable String manager, @PathVariable String operation, HttpSession session) {
//        logger.info("进入/" + manager + "/" + operation + "_judge");
//        Map<String, String> permissionCheck = new HashMap<>();
//        // 权限检验 return String msg(权限不足！请登录负责人或者超级管理员账号后再进行操作)
//        /*if (msg != null) {
//            permissionCheck.put("msg", msg);
//        }*/
//        /*
//            test代码片段
//            if (false) {
//                permissionCheck.put("msg", "test");
//            }
//        */
//        return permissionCheck;
//    }
//
//    //根据类名，获取类的对象实例
//    private Object getInstance(String className, HttpServletRequest request) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//
//        // 配置的Class
//        Class<?> clazz = Class.forName(className);
//        // 目标Class的实例对象
//        Object targetObject = clazz.newInstance();
//        // 遍历属性，赋值给实例对象     ---  注意区分，直接赋值，还是赋值引用对象
//        // 本程序特殊，没有引用对象
//
//        Enumeration<String> paramNames = request.getParameterNames();
//
//        while (paramNames.hasMoreElements()) {
//            try {
//                // 需要设置的成员变量名
//                String filedName = paramNames.nextElement();
//                // 构造setterXxx
//                String setterName = "set" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
//
//                String fieldValue = request.getParameter(filedName);
//
//                // 需要设置成员变量的setter方法
//                Field declaredField = clazz.getDeclaredField(filedName);
//                declaredField.setAccessible(true);
//                Method setterMethod = clazz.getMethod(setterName, declaredField.getType());
//
//                logger.info(filedName + " : " + fieldValue);
//
//                switch (declaredField.getType().getSimpleName()) {
//                    case "String":
//                        setterMethod.invoke(targetObject, fieldValue);
//                        break;
//                    case "BigDecimal":
//                        BigDecimal bigDecimal = new BigDecimal(fieldValue);
//                        setterMethod.invoke(targetObject, bigDecimal);
//                        break;
//                    case "Integer":
//                        Integer integer = Integer.parseInt(fieldValue);
//                        setterMethod.invoke(targetObject, integer);
//                        break;
//                    case "Date":
//                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        Date date = format.parse(fieldValue);
//                        setterMethod.invoke(targetObject, date);
//                        break;
//                    case "Long":
//                        Long aLong = new Long(fieldValue);
//                        setterMethod.invoke(targetObject, aLong);
//                        break;
//                    default:
//                        logger.error("还有没有实现的成员变量！！");
//                }
//            } catch (Exception e) {
//                logger.info("空串，转换不成功：" + e.getMessage());
//            }
//        }
//        return targetObject;
//    }
//}