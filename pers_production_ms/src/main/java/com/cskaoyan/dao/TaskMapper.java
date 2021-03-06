package com.cskaoyan.dao;

import com.cskaoyan.domain.Task;
import com.cskaoyan.domain.TaskExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskMapper {
	//扩展的mapper接口方法
	int deleteBatch(String[] ids);
	
	List<Task> find();

	List<Task> searchTaskByTaskId(String taskId);

	List<Task> searchTaskByTaskWorkId(String taskWorkId);

	List<Task> searchTaskByTaskManufactureSn(String taskManufactureSn);
	
	//逆向工程生成的mapper接口
    int countByExample(TaskExample example);

    int deleteByExample(TaskExample example);

    int deleteByPrimaryKey(String taskId);

    int insert(Task record);

    int insertSelective(Task record);

    List<Task> selectByExample(TaskExample example);

    Task selectByPrimaryKey(String taskId);

    int updateByExampleSelective(@Param("record") Task record, @Param("example") TaskExample example);

    int updateByExample(@Param("record") Task record, @Param("example") TaskExample example);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);
}