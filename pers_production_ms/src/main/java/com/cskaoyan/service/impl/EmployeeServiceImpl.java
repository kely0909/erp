package com.cskaoyan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cskaoyan.dao.DepartmentMapper;
import com.cskaoyan.dao.EmployeeMapper;
import com.cskaoyan.domain.Department;
import com.cskaoyan.domain.Employee;
import com.cskaoyan.domain.PageBean;
import com.cskaoyan.domain.Process;
import com.cskaoyan.domain.customize.EUDataGridResult;
import com.cskaoyan.domain.objUtils.CustomResult;
import com.cskaoyan.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


public class EmployeeServiceImpl implements EmployeeService {
	
	
        @Autowired
		EmployeeMapper employeeMapper;
        
        private static Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);
        
        
//	@Override
//	public CustomResult updateAll(Department department) {
//		// TODO Auto-generated method stub
//		
//		
//		
//		int i = departmentMapper.updateByPrimaryKey(department);
//		if(i>0){
//			return CustomResult.ok();
//		}else{
//			return CustomResult.build(101, "修改部门失败");
//		}
//	}
	
	@Override
	public CustomResult updateAll(Employee employee) {
		// TODO Auto-generated method stub
		
		int i = employeeMapper.updateByPrimaryKey(employee);
		if(i>0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "修改部门失败");
		}
		
		
	}
	

	
	
//	@Override
//	public PageBean<Employee> list(Integer page, Integer rows) {
//		// TODO Auto-generated method stub
//		
//		return null;
//	}
	
//	@Override
//	public PageBean<Department> list(Integer page, Integer rows) {
//		// TODO Auto-generated method stub
//		PageHelper.startPage(page, rows, true);
//		
//		List<Department> department = departmentMapper.selectAll();
//		
////		for(Department De:department){
////			De.setTechnologyPlan(technologyPlanMapper.selectByPrimaryKey(pr.getTechnologyPlanId()));
////        }
////		
//		PageBean<Department> pageBean = new PageBean<>(department);
//		logger.info("pageBean = " + pageBean);
//		return pageBean;
//	}
	
	@Override
	public PageBean<Employee> list(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows, true);
		
		List<Employee> employee = employeeMapper.selectAll();
		
//		for(Department De:department){
//			De.setTechnologyPlan(technologyPlanMapper.selectByPrimaryKey(pr.getTechnologyPlanId()));
//        }
//		
		PageBean<Employee> pageBean = new PageBean<>(employee);
		
		logger.info("pageBean = " + pageBean);
		
		return pageBean;
	}
	
	

//	@Override
//	public EUDataGridResult searchEmployeeByEmployeeName(Integer page, Integer rows, String searchValue) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
/**
 * 通过EmployeeName查询Employee
 * @param page
 * @param rows
 * @param searchValue
 * @return
 */
	//此处searchValue是否为departmentName，待确认
	@Override
	public EUDataGridResult searchEmployeeByEmployeeName(Integer page, Integer rows, String searchValue) {
		// TODO Auto-generated method stub
		//分页处理
				PageHelper.startPage(page, rows);
				List<Employee> list =  employeeMapper.searchEmployeeByEmployeeName(searchValue);
				//创建一个返回值对象
				EUDataGridResult result = new EUDataGridResult();
				result.setRows(list);
				//取记录总条数
				PageInfo<Employee> pageInfo = new PageInfo<>(list);
				result.setTotal(pageInfo.getTotal());
				return result;
		
	}

	
//	@Override
//	public CustomResult deleteBatch(String[] ids) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	@Override
//	public CustomResult deleteBatch(String[] ids) {
//		// TODO Auto-generated method stub
//		
//		int i = departmentMapper.deleteBatch(ids);
//		if(i>0){
//			return CustomResult.ok();
//		}else{
//			return null;
//		}
//	}
	
	@Override
	public CustomResult deleteBatch(String[] ids) {
		// TODO Auto-generated method stub
		
		int i = employeeMapper.deleteBatch(ids);
		if(i>0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}

	//保留该方法
//	@Override
//	public Object get(String departmentId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@Override
	public CustomResult insert(Employee employee) {
		// TODO Auto-generated method stub
		
		int i = employeeMapper.insert(employee);
		if(i>0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "新增雇员失败");
		}
	}




	@Override
	public CustomResult updateNote(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Object get(String employeeId) {
		// TODO Auto-generated method stub
		Employee employee = employeeMapper.selectByPrimaryKey(employeeId);
		return employee;
	}
	

//	@Override
//	public CustomResult insert(Employee employee) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public CustomResult updateNote(Employee employee) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	@Override
//	public CustomResult updateNote(Custom custom) throws Exception{
//		int i = customMapper.updateNote(custom);
//		if(i>0){
//			return CustomResult.ok();
//		}else{
//			return CustomResult.build(101, "更新客户介绍失败");
//		}
//	}

//	@Override
//	public CustomResult updateNote(Department department) {
//		// TODO Auto-generated method stub
//		int i = departmentMapper.updateNote(department);
//		if(i>0){
//			return CustomResult.ok();
//		}else{
//			return CustomResult.build(101, "更新部门介绍失败");
//		}
//	}
	

	
//	@Override
//	public int delete(String id) {
//		// TODO Auto-generated method stub
//		return departmentMapper.deleteByPrimaryKey(id);
//	}
	
//	@Override
//	public int delete(String id) {
//		// TODO Auto-generated method stub
//		return employeeMapper.deleteByPrimaryKey(id);
//	}
	
/////////////////////////////////////////////////////////////////////	
	
//	/**
//     * @param searchValue 从前端传入的值
//     * @param page
//     * @param rows
//     * @return
//     */	
//	@Override
//    public PageBean<Process> findProcessById(String searchValue, Integer page, Integer rows) {
//        searchValue = "%" + searchValue + "%";
//        PageHelper.startPage(page, rows, true);
//        List<Process> processes = processMapper.selectById(searchValue);
//
//        for(Process pr:processes){
//            pr.setTechnologyPlan(technologyPlanMapper.selectByPrimaryKey(pr.getTechnologyPlanId()));
//        }
//
//        PageBean<Process> pageBean = new PageBean<>(processes);
//        logger.info("pageBean = " + pageBean);
//        return pageBean;
//
//    }

//	@Override
//	public PageBean<Department> findDepartmentById(String searchValue, Integer page, Integer rows) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public PageBean<Department> findDepartmentById(String searchValue, Integer page, Integer rows) {
//		// TODO Auto-generated method stub
//		searchValue = "%" + searchValue + "%";
//		PageHelper.startPage(page, rows, true);
//		
//		//此处需修改为List<department>
//		List<Department> department = departmentMapper.selectByPrimaryKey(searchValue);
//		
////		for(Department de:department){
////			
////			de.setTechnologyPlan(technologyPlanMapper.selectByPrimaryKey(pr.getTechnologyPlanId()));
////		}
//		
//		PageBean<Department> pageBean = new PageBean<>(department);
//		logger.info("pageBean = " + pageBean);
//		return pageBean;
//		
//	}
	
	
//	  @Override
//	    public Process selectProcessById(String id) {
//	        return processMapper.selectByPrimaryKey(id);
//	    }
	 
//保留此方法
//	@Override
//	public List<Department> selectDepartmentById(String id) {
//		// TODO Auto-generated method stub
//		return departmentMapper.selectByPrimaryKey(id);
//	}

    /**
     * 返回所有bean的数据
     *
     * @return
     */
//    @Override
//    public List<Process> selectAllProcess() {
//        return processMapper.selectAll();
//    }
//	@Override
//	public List<Department> selectAllDepartment() {
//		// TODO Auto-generated method stub
//		
//		return departmentMapper.selectAll();
//	}

//	@Override
//	public EUDataGridResult searchCustomByCustomName(int page, int rows, String customName) throws Exception{
//		//分页处理
//		PageHelper.startPage(page, rows);
//		List<Custom> list =  customMapper.searchCustomByCustomName(customName);
//		//创建一个返回值对象
//		EUDataGridResult result = new EUDataGridResult();
//		result.setRows(list);
//		//取记录总条数
//		PageInfo<Custom> pageInfo = new PageInfo<>(list);
//		result.setTotal(pageInfo.getTotal());
//		return result;
//	}
	
	


//	@Override
//	public CustomResult deleteBatch(String[] ids) throws Exception{
//		int i = customMapper.deleteBatch(ids);
//		if(i>0){
//			return CustomResult.ok();
//		}else{
//			return null;
//		}
//	}


//	@Override
//	public CustomResult updateAll(Custom custom) throws Exception{
//		int i = customMapper.updateByPrimaryKey(custom);
//		if(i>0){
//			return CustomResult.ok();
//		}else{
//			return CustomResult.build(101, "修改客户失败");
//		}
//	}


//	@Override
//	public CustomResult insert(Department department) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public Object get(String departmentId) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public int update(Department department) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public List<Department> selectAllDepartment() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public PageBean<Process> findProcessByPlanId(String searchValue, Integer page, Integer rows) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public CustomResult insert(Department department) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public PageBean<Department> findDepartmentByPlanId(String searchValue, Integer page, Integer rows) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Department selectProcessById(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}


//	@Override
//	public CustomResult insert(Custom custom) throws Exception{
//		int i = customMapper.insert(custom);
//		if(i>0){
//			return CustomResult.ok();
//		}else{
//			return CustomResult.build(101, "新增客户失败");
//		}
//	}





//	@Override
//	public PageBean<Department> findDepartmentByPlanId(String searchValue, Integer page, Integer rows) {
//		// TODO Auto-generated method stub
//		return null;
//	}



//	@Override
//	public Department selectDepartmentById(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}



//	@Override
//	public Department selectProcessById(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}



//	@Override
//	public CustomResult insert(Department department) {
//		// TODO Auto-generated method stub
//		return null;
//	}







//	@Override
//	public Department selectDepartmentById(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
