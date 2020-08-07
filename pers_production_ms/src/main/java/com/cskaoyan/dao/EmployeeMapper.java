package com.cskaoyan.dao;

import com.cskaoyan.domain.Department;
import com.cskaoyan.domain.Employee;
import com.cskaoyan.domain.EmployeeExample;

import java.util.List;

public interface EmployeeMapper {
    long countByExample(EmployeeExample example);

    int deleteByPrimaryKey(String empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(String empId);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

	List<Employee> searchEmployeeByEmployeeName(String empName);

	int deleteBatch(String[] ids);

    
	List<Employee> selectAll();

	int updateNote(Employee employee);


}