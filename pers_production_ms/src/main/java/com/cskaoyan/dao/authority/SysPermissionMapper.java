package com.cskaoyan.dao.authority;

import com.cskaoyan.domain.authority.SysPermission;
import com.cskaoyan.domain.authority.SysPermissionExample;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface SysPermissionMapper {
	
	List<SysPermission> findPermission(String ids[]);
	
	
    int countByExample(SysPermissionExample example);

    int deleteByExample(SysPermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    List<SysPermission> selectByExample(SysPermissionExample example);

    SysPermission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysPermission record, @Param("example") SysPermissionExample example);

    int updateByExample(@Param("record") SysPermission record, @Param("example") SysPermissionExample example);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);
}