package com.cskaoyan.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cskaoyan.domain.Department;
import com.cskaoyan.domain.Employee;
import com.cskaoyan.domain.PageBean;
import com.cskaoyan.domain.customize.EUDataGridResult;
import com.cskaoyan.domain.objUtils.CustomResult;
import com.cskaoyan.service.DepartmentService;
import com.cskaoyan.service.EmployeeService;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
    private EmployeeService employeeService;
	
	private static Logger logger = LogManager.getLogger(EmployeeController.class);
	
//employee_list:
	
//	employee/delete_judge
	
	//
	@RequestMapping("/delete_judge")
	@ResponseBody
	public CustomResult deleteJudge(){
	    //先跳过验证
	    CustomResult customResult = new CustomResult();
	    customResult.setMsg(null);
	    return customResult;
	}
	
	
//	employee/edit_judge
	
	@RequestMapping("/edit_judge")
	@ResponseBody
	public CustomResult editJudge(){
	    //先跳过验证
	    CustomResult customResult = new CustomResult();
	    customResult.setMsg(null);
	    return customResult;
	}
	
//	employee/add_judge
	
	@RequestMapping(value = "/add_judge", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> add_judge(HttpSession session) {
	    logger.info("进入/add_judge，分页信息如下");
	    HashMap<String, String> permissionCheck = new HashMap<>();
//	    // 权限检验 return String msg(权限不足！请登录负责人或者超级管理员账号后再进行操作)
//	    if (msg != null) {
//	        permissionCheck.put("msg", msg);
//	    }
//	    
////	        test代码片段
//	        if (false) {
//	            permissionCheck.put("msg", "test");
//	        }
	    
	    return permissionCheck;
	}
	
//	department/update_all
	
	
	@RequestMapping(value="/update_all")
	@ResponseBody
	private CustomResult updateAll(@Valid Employee employee, BindingResult bindingResult)
			throws Exception {
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		return employeeService.updateAll(employee);
	}
	
//	department/edit_judge
//	employee/list
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public PageBean<Employee> list(Integer page, Integer rows) {
	    logger.info("进入/department/list，分页信息如下");
	    logger.info("page = " + page);
	    logger.info("rows = " + rows);

	    return employeeService.list(page, rows);
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
	
	
//	employee/edit
	
	//@RequestMapping("/edit")
		//public String edit(){
//		    logger.info("权限验证通过！进入/technologyRequirement/edit");
//		    return "process_edit";
		//}
		//
		//@RequestMapping("/edit")
		//public String edit() throws Exception{
//			return "order_edit";
		//}

//		@RequestMapping("/edit")
//		public String edit(){
//		    logger.info("权限验证通过！进入/department/edit");
//		    return "department_edit";
//		}
	
	@RequestMapping("/edit")
	@ResponseBody
	public String edit(){
	    logger.info("权限验证通过！进入/department/edit");
	    return "employee_edit";
	}
	

//	employee/add
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(){
		logger.info("权限验证通过！进入/department/edit");
		return "employee_add";
	}
	
//	employee/search_employee_by_'+name+'?searchValue='+value,
			//@RequestMapping("/deviceCheck/search_device_by_deviceTypeName")
			//@ResponseBody
			//public PageBean searchByName (String searchValue,Integer page, Integer rows){
			//  return deviceService.searchByName(searchValue, page, rows);
			//}
	
	//根据客户名查找
	@RequestMapping("/search_employee_by_'+name+'?searchValue='+value+'")
	@ResponseBody
	public EUDataGridResult searchEmployeeByEmployeeName(Integer page, Integer rows, String searchValue) throws Exception{
		EUDataGridResult result = employeeService.searchEmployeeByEmployeeName(page, rows, searchValue);
		return result;
	}

  

	



			
			
//	employee/delete_batch
	//@RequestMapping(value="/delete_batch")
	//@ResponseBody
	//private CustomResult deleteBatch(String[] ids) throws Exception {
//		CustomResult result = orderService.deleteBatch(ids);
//		return result;
	//}
	//
	//@RequestMapping("delete_batch")
	//@ResponseBody
	//public Map<String, String> delete_batch (String ids){
//	    logger.info("进入/process/delete_batch");
//	    HashMap<String, String> deleteCheck = new HashMap<>();
//	    String[] ids1 = ids.split(",");
	//
//	    for (int i = 0; i < ids1.length; i++) {
//	        processService.delete(ids1[i]);
//	    }
//	    deleteCheck.put("status", "200");
//	    return deleteCheck;
	//}
	//
	//@RequestMapping(value="/delete_batch")
	//@ResponseBody
	//private CustomResult deleteBatch(String[] ids) throws Exception {
//		CustomResult result = productService.deleteBatch(ids);
//		return result;
	//}
	@RequestMapping(value="/delete_batch")
	@ResponseBody
	private CustomResult deleteBatch(String[] ids) throws Exception {
		CustomResult result = employeeService.deleteBatch(ids);
		return result;
	}
	
//employee_add:	
//	employee/insert
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
	private CustomResult insert(@Valid Employee employee, BindingResult bindingResult) throws Exception {
		CustomResult result;
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		if(employeeService.get(employee.getEmployeeId()) != null){
			result = new CustomResult(0, "该员工编号已经存在，请更换部门编号！", null);
		}else{
			result = employeeService.insert(employee);
		}
		return result;
	}

//employee_list:	
//	employee/update_all
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
	
//	@RequestMapping(value="/update_all")
//	@ResponseBody
//	private CustomResult updateAll(@Valid Employee employee, BindingResult bindingResult)
//			throws Exception {
//		if(bindingResult.hasErrors()){
//			FieldError fieldError = bindingResult.getFieldError();
//			return CustomResult.build(100, fieldError.getDefaultMessage());
//		}
//		return employeeService.updateAll(employee);
//	}
	

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
private CustomResult updateNote(@Valid Employee employee, BindingResult bindingResult) throws Exception {
	if(bindingResult.hasErrors()){
		FieldError fieldError = bindingResult.getFieldError();
		return CustomResult.build(100, fieldError.getDefaultMessage());
	}
	return employeeService.updateNote(employee);
}

	/////////////////////////////////////////////////////////////////////////


	
		

}
