package com.cskaoyan.service;

import com.cskaoyan.domain.Department;
import com.cskaoyan.domain.Employee;
import com.cskaoyan.domain.PageBean;
import com.cskaoyan.domain.customize.EUDataGridResult;
import com.cskaoyan.domain.objUtils.CustomResult;

public interface EmployeeService {

	CustomResult updateAll(Employee employee);

	PageBean<Employee> list(Integer page, Integer rows);

	EUDataGridResult searchEmployeeByEmployeeName(Integer page, Integer rows, String searchValue);

	CustomResult deleteBatch(String[] ids);

	Object get(String employeeId);

	CustomResult insert(Employee employee);

	CustomResult updateNote(Employee employee);

	

	

}
