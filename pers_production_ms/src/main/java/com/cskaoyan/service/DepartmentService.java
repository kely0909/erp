package com.cskaoyan.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cskaoyan.domain.COrder;
import com.cskaoyan.domain.Department;
import com.cskaoyan.domain.PageBean;
import com.cskaoyan.domain.customize.EUDataGridResult;
import com.cskaoyan.domain.objUtils.CustomResult;

//



public interface DepartmentService {
	

	    /**
	     * 分页查找
	     * @param page 当前页码
	     * @param rows 条/页
	     * @return != null 查找成功；null 查找失败
	     */
	    PageBean<Department> list(Integer page, Integer rows);

//	    /**
//	     *
//	     * @param process
//	     * @return 1表示插入成功
//	     */
	    CustomResult insert(Department department);

	    /**
	     * 更新数据
	     * @param process
	     * @return
	     */
	    int update(Department department);

	    /**
	     * @param id
	     * @return
	     */
	    int delete(String id);

	    /**
	     * @param searchValue  从前端传入的值
	     * @param page
	     * @param rows
	     * @return
	     */
	    PageBean<Department> findDepartmentById(String searchValue, Integer page, Integer rows);

	    /**
	     * @param searchValue
	     * @param page
	     * @param rows
	     * @return
	     */
	    PageBean<Department> findDepartmentByPlanId(String searchValue, Integer page, Integer rows);

	    Department selectDepartmentById(String id);

	    /**
	     * 返回所有bean的数据
	     *
	     * @return
	     */	    
	    List<Department> selectAllDepartment();

	//	
//		@RequestMapping("/list")
//		@ResponseBody
//		public EUDataGridResult getItemList(Integer page, Integer rows, Custom custom) throws Exception{
//			EUDataGridResult result = customService.getList(page, rows, custom);
//			return result;
//		}
		EUDataGridResult searchDepartmentByDepartmentName(Integer page, Integer rows, String searchValue);

//		@RequestMapping(value="/update_all")
//		@ResponseBody
//		private CustomResult updateAll(@Valid TechnologyRequirement technologyRequirement, BindingResult bindingResult)
//				throws Exception {
//			if(bindingResult.hasErrors()){
//				FieldError fieldError = bindingResult.getFieldError();
//				return CustomResult.build(100, fieldError.getDefaultMessage());
//			}
//			return technologyRequirementService.updateAll(technologyRequirement);
//		}
		CustomResult updateNote(Department department);
	
//		department/delete_batch
//		@RequestMapping(value="/delete_batch")
//		@ResponseBody
//		private CustomResult deleteBatch(String[] ids) throws Exception {
//			CustomResult result = customService.deleteBatch(ids);
//			return result;
//		}
		CustomResult deleteBatch(String[] ids);

//		@RequestMapping(value="/update_all")
//		@ResponseBody
//		private CustomResult updateAll(@Valid Department department, BindingResult bindingResult)
//				throws Exception {
//			if(bindingResult.hasErrors()){
//				FieldError fieldError = bindingResult.getFieldError();
//				return CustomResult.build(100, fieldError.getDefaultMessage());
//			}
//			return departmentService.updateAll(department);
//		}
		CustomResult updateAll(Department department);

		Object get(String departmentId);

//		Object get(String departmentId);

//		department/insert
//		@RequestMapping(value="/insert", method=RequestMethod.POST)
//		@ResponseBody
//		private CustomResult insert(@Valid COrder cOrder, BindingResult bindingResult) throws Exception {
//			CustomResult result;
//			if(bindingResult.hasErrors()){
//				FieldError fieldError = bindingResult.getFieldError();
//				System.out.println(fieldError.getDefaultMessage());
//				return CustomResult.build(100, fieldError.getDefaultMessage());
//			}
//			if(orderService.get(cOrder.getOrderId()) != null){
//				result = new CustomResult(0, "该订单编号已经存在，请更换订单编号！", null);
//			}else{
//				result = orderService.insert(cOrder);
//			}
//			return result;
//		}
//		CustomResult insert(Department department);

//		Object get(String departmentId);
	


}
