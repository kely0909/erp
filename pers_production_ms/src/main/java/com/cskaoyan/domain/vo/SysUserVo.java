package com.cskaoyan.domain.vo;

import com.cskaoyan.domain.authority.SysUser;

public class SysUserVo extends SysUser {

    private String rolename;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
