package com.cskaoyan.service.impl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//import org.hibernate.validator.internal.util.logging.Log_.logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.cskaoyan.dao.DepartmentMapper;
import com.cskaoyan.dao.ProcessMapper;
import com.cskaoyan.domain.Custom;
import com.cskaoyan.domain.Department;
import com.cskaoyan.domain.PageBean;
import com.cskaoyan.domain.Process;
import com.cskaoyan.domain.customize.EUDataGridResult;
import com.cskaoyan.domain.objUtils.CustomResult;
import com.cskaoyan.service.DepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class DepartmentServiceImpl implements DepartmentService {
	
	  @Autowired
	    DepartmentMapper departmentMapper;
	  
	  private static Logger logger = LogManager.getLogger(DepartmentServiceImpl.class);

//	  /**
//	     * 分页查找
//	     *
//	     * @param page 当前页码
//	     * @param rows 条/页
//	     * @return != null 查找成功；null 查找失败
//	     */
//	    @Override
//	    public PageBean<Process> list(Integer page, Integer rows) {
//	        PageHelper.startPage(page, rows, true);
//	        List<Process> processes = processMapper.selectAll();
//
//	        for(Process pr:processes){
//	            pr.setTechnologyPlan(technologyPlanMapper.selectByPrimaryKey(pr.getTechnologyPlanId()));
//	        }
//
//	        PageBean<Process> pageBean = new PageBean<>(processes);
//	        logger.info("pageBean = " + pageBean);
//	        return pageBean;
//	    }
	    
	  /**
	   * 
	   */
	@Override
	public PageBean<Department> list(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows, true);
		
		List<Department> department = departmentMapper.selectAll();
		
//		for(Department De:department){
//			De.setTechnologyPlan(technologyPlanMapper.selectByPrimaryKey(pr.getTechnologyPlanId()));
//        }
//		
		PageBean<Department> pageBean = new PageBean<>(department);
		logger.info("pageBean = " + pageBean);
		return pageBean;
	}


	
//	@Override
//	public int insert(Department department) {
//		// TODO Auto-generated method stub
//		return departmentMapper.insert(department);
//		
//	}

//	@Override
//	public int update(Process process) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
	
	@Override
	public int update(Department department) {
		// TODO Auto-generated method stub
		return departmentMapper.updateByPrimaryKey(department);
	}

//	@Override
//	public int delete(String id) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
	
	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return departmentMapper.deleteByPrimaryKey(id);
	}
	
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
	@Override
	public PageBean<Department> findDepartmentById(String searchValue, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		searchValue = "%" + searchValue + "%";
		PageHelper.startPage(page, rows, true);
		
		//此处需修改为List<department>
		List<Department> department = departmentMapper.selectById(searchValue);
		
//		for(Department de:department){
//			
//			de.setTechnologyPlan(departmentMapper.selectByPrimaryKey(de.getDepartmentId()));
//			de.se(departmentMapper.selectByPrimaryKey(de.getDepartmentId()));
//		}
		
		PageBean<Department> pageBean = new PageBean<>(department);
		logger.info("pageBean = " + pageBean);
		return pageBean;
		
	}
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
	@Override
	public List<Department> selectAllDepartment() {
		// TODO Auto-generated method stub
		
		return departmentMapper.selectAll();
	}

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
	/**
	 * 
	 */
	//此处searchValue是否为departmentName，待确认
	@Override
	public EUDataGridResult searchDepartmentByDepartmentName(Integer page, Integer rows, String searchValue) {
		// TODO Auto-generated method stub
		//分页处理
				PageHelper.startPage(page, rows);
				List<Department> list =  departmentMapper.searchDepartmentByDepartmentName(searchValue);
				//创建一个返回值对象
				EUDataGridResult result = new EUDataGridResult();
				result.setRows(list);
				//取记录总条数
				PageInfo<Department> pageInfo = new PageInfo<>(list);
				result.setTotal(pageInfo.getTotal());
				return result;
		
	}
	
//	@Override
//	public CustomResult updateNote(Custom custom) throws Exception{
//		int i = customMapper.updateNote(custom);
//		if(i>0){
//			return CustomResult.ok();
//		}else{
//			return CustomResult.build(101, "更新客户介绍失败");
//		}
//	}

	@Override
	public CustomResult updateNote(Department department) {
		// TODO Auto-generated method stub
		int i = departmentMapper.updateNote(department);
		if(i>0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "更新部门介绍失败");
		}
	}

//	@Override
//	public CustomResult deleteBatch(String[] ids) throws Exception{
//		int i = customMapper.deleteBatch(ids);
//		if(i>0){
//			return CustomResult.ok();
//		}else{
//			return null;
//		}
//	}
	@Override
	public CustomResult deleteBatch(String[] ids) {
		// TODO Auto-generated method stub
		
		int i = departmentMapper.deleteBatch(ids);
		if(i>0){
			return CustomResult.ok();
		}else{
			return null;
		}
	}

//	@Override
//	public CustomResult updateAll(Custom custom) throws Exception{
//		int i = customMapper.updateByPrimaryKey(custom);
//		if(i>0){
//			return CustomResult.ok();
//		}else{
//			return CustomResult.build(101, "修改客户失败");
//		}
//	}
	@Override
	public CustomResult updateAll(Department department) {
		// TODO Auto-generated method stub
		
		int i = departmentMapper.updateByPrimaryKey(department);
		if(i>0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "修改部门失败");
		}
	}

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

	@Override
	public Object get(String departmentId) {
		// TODO Auto-generated method stub
		Department department = departmentMapper.selectByPrimaryKey(departmentId);
		return department;
	}

//	@Override
//	public CustomResult insert(Custom custom) throws Exception{
//		int i = customMapper.insert(custom);
//		if(i>0){
//			return CustomResult.ok();
//		}else{
//			return CustomResult.build(101, "新增客户失败");
//		}
//	}

	@Override
	public CustomResult insert(Department department) {
		// TODO Auto-generated method stub
		
		int i = departmentMapper.insert(department);
		if(i>0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "新增部门失败");
		}
	}



	@Override
	public PageBean<Department> findDepartmentByPlanId(String searchValue, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Department selectDepartmentById(String id) {
		// TODO Auto-generated method stub
		return null;
	}



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
