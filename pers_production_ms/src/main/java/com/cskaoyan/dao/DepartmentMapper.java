package com.cskaoyan.dao;

import com.cskaoyan.domain.Department;
import com.cskaoyan.domain.DepartmentExample;

import java.util.List;

public interface DepartmentMapper {
    long countByExample(DepartmentExample example);

    int deleteByPrimaryKey(String departmentId);

    int insert(Department record);

    int insertSelective(Department record);

    List<Department> selectByExample(DepartmentExample example);

    Department selectByPrimaryKey(String departmentId);
    
    

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
    
    List<Department> selectAll();

	List<Department> searchDepartmentByDepartmentName(String searchValue);

	int updateNote(Department department);

	int deleteBatch(String[] ids);

	List<Department> selectById(String searchValue);
}