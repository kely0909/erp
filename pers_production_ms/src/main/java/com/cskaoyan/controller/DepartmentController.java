package com.cskaoyan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cskaoyan.domain.COrder;
import com.cskaoyan.domain.Custom;
import com.cskaoyan.domain.Department;
import com.cskaoyan.domain.DeviceExt;
import com.cskaoyan.domain.PageBean;
import com.cskaoyan.domain.Process;
import com.cskaoyan.domain.Product;
import com.cskaoyan.domain.Technology;
import com.cskaoyan.domain.TechnologyRequirement;
import com.cskaoyan.domain.customize.EUDataGridResult;
import com.cskaoyan.domain.objUtils.CustomResult;
import com.cskaoyan.domain.vo.COrderVO;
import com.cskaoyan.service.CustomService;
import com.cskaoyan.service.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
    private DepartmentService departmentService;
	private static Logger logger = LogManager.getLogger(DepartmentController.class);

//department_list.jsp:
//	
//department/list
	/*
	 * @RequestMapping("/list")
	 * 
	 * @ResponseBody public EUDataGridResult getItemList(Integer page, Integer rows,
	 * Custom custom) throws Exception{ EUDataGridResult result =
	 * customService.getList(page, rows, custom); return result; }
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping("/deviceList/list") public PageBean getlist(Integer page,
	 * Integer rows){ PageBean<DeviceExt> list = deviceService.list(page, rows);
	 * return list; }
	 * 
	 * @RequestMapping("/list")
	 * 
	 * @ResponseBody public EUDataGridResult getList(Integer page, Integer rows,
	 * COrderVO cOrder) throws Exception{ EUDataGridResult result =
	 * orderService.getList(page, rows, cOrder); return result; }
	 */

@RequestMapping(value = "/list", method = RequestMethod.GET)
@ResponseBody
public PageBean<Department> list(Integer page, Integer rows) {
    logger.info("进入/department/list，分页信息如下");
    logger.info("page = " + page);
    logger.info("rows = " + rows);

    return departmentService.list(page, rows);
}

	/*
	 * @RequestMapping(value = "/list", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public PageBean<Department> list(Integer page, Integer rows) {
	 * logger.info("进入/process/list，分页信息如下"); logger.info("page = " + page);
	 * logger.info("rows = " + rows);
	 * 
	 * return processService.list(page, rows); }
	 * 
	 * @RequestMapping("/list")
	 * 
	 * @ResponseBody public EUDataGridResult getItemList(Integer page, Integer rows,
	 * Technology technology) throws Exception{ EUDataGridResult result =
	 * technologyService.getList(page, rows, technology); return result; }
	 */

//department/edit
//@RequestMapping("/edit")
//public String edit(){
//    logger.info("权限验证通过！进入/technologyRequirement/edit");
//    return "process_edit";
//}
//
//@RequestMapping("/edit")
//public String edit() throws Exception{
//	return "order_edit";
//}

@RequestMapping("/edit")
public String edit(){
    logger.info("权限验证通过！进入/department/edit");
    return "department_edit";
}



//department/add
//@RequestMapping("/add")
//public String add() {
//	return "custom_add";
//}

@RequestMapping("/add")
public String add() {
	return "department_add";
}






//department/search_department_by_'+name+'?searchValue='+value, 
////根据客户名查找
//	@RequestMapping("/search_custom_by_customName")
//	@ResponseBody
//	public EUDataGridResult searchCustomByCustomName(Integer page, Integer rows, String searchValue) 
//			throws Exception{
//		EUDataGridResult result = customService.searchCustomByCustomName(page, rows, searchValue);
//		return result;
//	}
//
//@RequestMapping("/deviceCheck/search_device_by_deviceTypeName")
//@ResponseBody
//public PageBean searchByName (String searchValue,Integer page, Integer rows){
//  return deviceService.searchByName(searchValue, page, rows);
//}


//根据客户名查找
	@RequestMapping("/search_department_by_'+name+'?searchValue='+value+'")
	@ResponseBody
	public EUDataGridResult searchDepartmentByDepartmentName(Integer page, Integer rows, String searchValue) throws Exception{
		EUDataGridResult result = departmentService.searchDepartmentByDepartmentName(page, rows, searchValue);
		return result;
	}
//
//////根据客户名称查找
////	@RequestMapping("/search_order_by_orderCustom")
////	@ResponseBody
////	public EUDataGridResult searchOrderByOrderCustom(Integer page, Integer rows, String searchValue) throws Exception{
////		EUDataGridResult result = orderService.searchOrderByCustomName(page, rows, searchValue);
////		return result;
////	}
////	
////	//根据产品名称查找
////		@RequestMapping("/search_product_by_productName")
////		@ResponseBody
////		public EUDataGridResult searchProductByProductName(Integer page, Integer rows, String searchValue) throws Exception{
////			EUDataGridResult result = productService.searchProductByProductName(page, rows, searchValue);
////			return result;
////		}
//		
////		//根据工艺名称查找
////		@RequestMapping("/search_technology_by_technologyName")
////		@ResponseBody
////		public EUDataGridResult searchTechnologyByTechnologyName(Integer page, Integer rows, String searchValue)
////				throws Exception{
////			EUDataGridResult result = technologyService.searchTechnologyByTechnologyName(page, rows, searchValue);
////			return result;
////		}





//department/update_note
@RequestMapping(value="/update_note")
@ResponseBody
private CustomResult updateNote(@Valid Department department, BindingResult bindingResult) throws Exception {
	if(bindingResult.hasErrors()){
		FieldError fieldError = bindingResult.getFieldError();
		return CustomResult.build(100, fieldError.getDefaultMessage());
	}
	return departmentService.updateNote(department);
}

//@RequestMapping(value="/update_note")
//@ResponseBody
//private CustomResult updateNote(@Valid Product product, BindingResult bindingResult) throws Exception {
//	if(bindingResult.hasErrors()){
//		FieldError fieldError = bindingResult.getFieldError();
//		return CustomResult.build(100, fieldError.getDefaultMessage());
//	}
//	return productService.updateNote(product);
//}


//@RequestMapping(value="/update_note")
//@ResponseBody
//private CustomResult updateNote(@Valid Custom custom, BindingResult bindingResult) throws Exception {
//	if(bindingResult.hasErrors()){
//		FieldError fieldError = bindingResult.getFieldError();
//		return CustomResult.build(100, fieldError.getDefaultMessage());
//	}
//	return customService.updateNote(custom);
//}

//department/add_judge
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


//department/delete_judge
//@ResponseBody
//@RequestMapping("/deviceType/delete_judge")
//public String deviceType_delete_judge() {
//    return null;
//}

@RequestMapping("/delete_judge")
@ResponseBody
public CustomResult deleteJudge(){
    //先跳过验证
    CustomResult customResult = new CustomResult();
    customResult.setMsg(null);
    return customResult;
}

//@RequestMapping("/delete_judge")
//@ResponseBody
//public CustomResult deleteJudge(){
//    //先跳过验证
//    CustomResult customResult = new CustomResult();
//    customResult.setMsg(null);
//    return customResult;
//}

/**
 *物料消耗删除
 */
//@RequestMapping("materialConsume/delete_judge")
//@ResponseBody
//public Map<String, String> materialConsumeDelete(){
//
//    logger.info("进入到materialConsume/delete_judge");
//
//    //判断
//    return null;
//}

//@RequestMapping("/delete_judge")
//@ResponseBody
//public CustomResult deleteJudge(){
//    //先跳过验证
//    CustomResult customResult = new CustomResult();
//    customResult.setMsg(null);
//    return customResult;
//}


//department/delete_batch
//@RequestMapping(value="/delete_batch")
//@ResponseBody
//private CustomResult deleteBatch(String[] ids) throws Exception {
//	CustomResult result = orderService.deleteBatch(ids);
//	return result;
//}
//
//@RequestMapping("delete_batch")
//@ResponseBody
//public Map<String, String> delete_batch (String ids){
//    logger.info("进入/process/delete_batch");
//    HashMap<String, String> deleteCheck = new HashMap<>();
//    String[] ids1 = ids.split(",");
//
//    for (int i = 0; i < ids1.length; i++) {
//        processService.delete(ids1[i]);
//    }
//    deleteCheck.put("status", "200");
//    return deleteCheck;
//}
//
//@RequestMapping(value="/delete_batch")
//@ResponseBody
//private CustomResult deleteBatch(String[] ids) throws Exception {
//	CustomResult result = productService.deleteBatch(ids);
//	return result;
//}
@RequestMapping(value="/delete_batch")
@ResponseBody
private CustomResult deleteBatch(String[] ids) throws Exception {
	CustomResult result = departmentService.deleteBatch(ids);
	return result;
}

/////////////////////////////////////////////////////////////////////////////
//
//department_edit.jsp:
//	
//	
//	department/edit_judge
//	@ResponseBody
//	@RequestMapping("/deviceType/edit_judge")
//	public String deviceType_edit_judge() {
//	    return null;
//	}

	@RequestMapping("/edit_judge")
	@ResponseBody
	public CustomResult editJudge(){
	    //先跳过验证
	    CustomResult customResult = new CustomResult();
	    customResult.setMsg(null);
	    return customResult;
	}

//	@RequestMapping(value = "edit_judge",method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, String> edit_judge(HttpSession session){
//	    logger.info("进入/process/edit_judge");
//	    HashMap<String, String> permissionCheck = new HashMap<>();
//
//	    // 权限检验 return String msg(权限不足！请登录负责人或者超级管理员账号后再进行操作)
//	    /*if (msg != null) {
//	        permissionCheck.put("msg", msg);
//	    }*/
//	    /*
//	        test代码片段
//	        if (false) {
//	            permissionCheck.put("msg", "test");
//	        }
//	    */
//	    return permissionCheck;
//	}
	
	
//	department/update_all
//	@RequestMapping(value="/update_all")
//	@ResponseBody
//	private CustomResult updateAll(@Valid Technology technology, BindingResult bindingResult) throws Exception {
//		if(bindingResult.hasErrors()){
//			FieldError fieldError = bindingResult.getFieldError();
//			return CustomResult.build(100, fieldError.getDefaultMessage());
//		}
//		return technologyService.updateAll(technology);
//	}
//	
//	@RequestMapping(value="/update_all")
//	@ResponseBody
//	private CustomResult updateAll(@Valid Technology technology, BindingResult bindingResult) throws Exception {
//		if(bindingResult.hasErrors()){
//			FieldError fieldError = bindingResult.getFieldError();
//			return CustomResult.build(100, fieldError.getDefaultMessage());
//		}
//		return technologyService.updateAll(technology);
//	}
//	
//
//	@RequestMapping(value="/update_all")
//	@ResponseBody
//	private CustomResult updateAll(@Valid Custom custom, BindingResult bindingResult) throws Exception {
//		if(bindingResult.hasErrors()){
//			FieldError fieldError = bindingResult.getFieldError();
//			return CustomResult.build(100, fieldError.getDefaultMessage());
//		}
//		return customService.updateAll(custom);
//	}
//	
//	@RequestMapping(value="/update_all")
//	@ResponseBody
//	private CustomResult updateAll(@Valid TechnologyRequirement technologyRequirement, BindingResult bindingResult)
//			throws Exception {
//		if(bindingResult.hasErrors()){
//			FieldError fieldError = bindingResult.getFieldError();
//			return CustomResult.build(100, fieldError.getDefaultMessage());
//		}
//		return technologyRequirementService.updateAll(technologyRequirement);
//	}
//	
//	@RequestMapping(value="/update_all")
//	@ResponseBody
//	private CustomResult updateAll(@Valid TechnologyRequirement technologyRequirement, BindingResult bindingResult)
//			throws Exception {
//		if(bindingResult.hasErrors()){
//			FieldError fieldError = bindingResult.getFieldError();
//			return CustomResult.build(100, fieldError.getDefaultMessage());
//		}
//		return technologyRequirementService.updateAll(technologyRequirement);
//	}
	
	@RequestMapping(value="/update_all")
	@ResponseBody
	private CustomResult updateAll(@Valid Department department, BindingResult bindingResult)
			throws Exception {
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		return departmentService.updateAll(department);
	}

	/////////////////////////////////////////////////////////////////////////

//department_add.jsp:

//	department/insert
//	@RequestMapping(value="/insert", method=RequestMethod.POST)
//	@ResponseBody
//	private CustomResult insert(@Valid COrder cOrder, BindingResult bindingResult) throws Exception {
//		CustomResult result;
//		if(bindingResult.hasErrors()){
//			FieldError fieldError = bindingResult.getFieldError();
//			System.out.println(fieldError.getDefaultMessage());
//			return CustomResult.build(100, fieldError.getDefaultMessage());
//		}
//		if(orderService.get(cOrder.getOrderId()) != null){
//			result = new CustomResult(0, "该订单编号已经存在，请更换订单编号！", null);
//		}else{
//			result = orderService.insert(cOrder);
//		}
//		return result;
//	}
//	
//	@RequestMapping(value="/insert", method=RequestMethod.POST)
//	@ResponseBody
//	private CustomResult insert(@Valid Custom custom, BindingResult bindingResult) throws Exception {
//		CustomResult result;
//		if(bindingResult.hasErrors()){
//			FieldError fieldError = bindingResult.getFieldError();
//			return CustomResult.build(100, fieldError.getDefaultMessage());
//		}
//		if(customService.get(custom.getCustomId()) != null){
//			result = new CustomResult(0, "该客户编号已经存在，请更换客户编号！", null);
//		}else{
//			result = customService.insert(custom);
//		}
//		return result;
//	}
//	
//	
//	@RequestMapping(value = "/insert", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, String>  insert (Process process){
//        logger.info("/process/insert");
//        Map<String, String> insertCheck = new HashMap<>();
//
//        try {
//            if (processService.insert(process) == 1) {
//                insertCheck.put("status", "200");
//            }
//        } catch (Exception e) {
//            logger.info("insert异常！", e);
//            insertCheck.put("msg", "添加异常，请联系管理员！");
//        } finally {
//            //前端显示
//            return insertCheck;
//        }
//    }
//	
//	@RequestMapping(value="/insert", method=RequestMethod.POST)
//	@ResponseBody
//	private CustomResult insert(@Valid Technology technology, BindingResult bindingResult) throws Exception {
//		CustomResult result;
//		if(bindingResult.hasErrors()){
//			FieldError fieldError = bindingResult.getFieldError();
//			return CustomResult.build(100, fieldError.getDefaultMessage());
//		}
//		if(technologyService.get(technology.getTechnologyId()) != null){
//			result = new CustomResult(0, "该工艺编号已经存在，请更换工艺编号！", null);
//		}else{
//			result = technologyService.insert(technology);
//		}
//		return result;
//	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	private CustomResult insert(@Valid Department department, BindingResult bindingResult) throws Exception {
		CustomResult result;
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		if(departmentService.get(department.getDepartmentId()) != null){
			result = new CustomResult(0, "该部门编号已经存在，请更换部门编号！", null);
		}else{
			result = departmentService.insert(department);
		}
		return result;
	}



	
//	@Autowired
//	private CustomService customService;
//
//
//    
//    
//	@RequestMapping("/get/{customId}")
//	@ResponseBody
//	public Custom getItemById(@PathVariable String customId) throws Exception{
//		Custom custom = customService.get(customId);
//		return custom;
//	}
//	
//	@RequestMapping("/find")
//	public String find() throws Exception{
//		return "custom_list";
//	}
//	
//
//	
//	@RequestMapping("/edit")
//	public String edit() throws Exception{
//		return "custom_edit";
//	}
//	
//	@RequestMapping("/get_data")
//	@ResponseBody
//	public List<Custom> getData() throws Exception{
//		 List<Custom> list = customService.find();
//		return list;
//	}
//	
//	@RequestMapping("/list")
//	@ResponseBody
//	public EUDataGridResult getItemList(Integer page, Integer rows, Custom custom) throws Exception{
//		EUDataGridResult result = customService.getList(page, rows, custom);
//		return result;
//	}
//	
//	
//	
//	@RequestMapping(value="/update")
//	@ResponseBody
//	private CustomResult update(@Valid Custom custom, BindingResult bindingResult) throws Exception {
//		if(bindingResult.hasErrors()){
//			FieldError fieldError = bindingResult.getFieldError();
//			return CustomResult.build(100, fieldError.getDefaultMessage());
//		}
//		return customService.update(custom);
//	}
//	
//	
//	
//	
//	
//	@RequestMapping(value="/delete")
//	@ResponseBody
//	private CustomResult delete(String id) throws Exception {
//		CustomResult result = customService.delete(id);
//		return result;
//	}
//	
//	department/delete_batch
//	@RequestMapping(value="/delete_batch")
//	@ResponseBody
//	private CustomResult deleteBatch(String[] ids) throws Exception {
//		CustomResult result = customService.deleteBatch(ids);
//		return result;
//	}
//	
//	@RequestMapping(value="/change_status")
//	@ResponseBody
//	public CustomResult changeStatus(String[] ids) throws Exception{
//		CustomResult result = customService.changeStatus(ids);
//		return result;
//	}
//	
//	//根据客户id查找
//	@RequestMapping("/search_custom_by_customId")
//	@ResponseBody
//	public EUDataGridResult searchCustomByCustomId(Integer page, Integer rows, String searchValue)
//			throws Exception{
//		EUDataGridResult result = customService.searchCustomByCustomId(page, rows, searchValue);
//		return result;
//	}
//	
//	//根据客户名查找
//	@RequestMapping("/search_custom_by_customName")
//	@ResponseBody
//	public EUDataGridResult searchCustomByCustomName(Integer page, Integer rows, String searchValue) 
//			throws Exception{
//		EUDataGridResult result = customService.searchCustomByCustomName(page, rows, searchValue);
//		return result;
//	}
	
}













