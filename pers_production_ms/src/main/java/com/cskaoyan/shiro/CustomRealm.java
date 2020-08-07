package com.cskaoyan.shiro;


import com.cskaoyan.domain.authority.SysPermission;
import com.cskaoyan.domain.authority.SysRolePermission;
import com.cskaoyan.service.SuperService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomRealm extends AuthorizingRealm {

    Logger logger = LogManager.getLogger(CustomRealm.class);

    @Autowired
    SuperService superService;

    @Override
    public String getName() {
        return "CustomRealm";
    }

    //认证的时候，需要我们去调用自己的realm，返回用户认证的信息，从数据库里返回
    //这里只是返回当前用户的认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String principal = (String) authenticationToken.getPrincipal();
        logger.info("principal = " + principal);

        Map map = null;

        //去数据库里查询用户名叫张三的用户信息
        try {
            List selectByCustom = (List) superService.selectByCustom("sysUser", "username", principal);
            map = (Map) selectByCustom.get(0);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        //参数一也就是身份信息，会被放入到Session域里。
        /*SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                sysUser,
                sysUser.getPassword(),
                Md5Hash.Util.bytes(solt),
                getName()
        );*/

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                principal,
                map.get("password"),
                getName()
        );

        return simpleAuthenticationInfo;
    }


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = null;
        List<String> permissions = new ArrayList<>();

        //根据用户信息去查询该用户的授权信息，
        String principal = (String) principalCollection.getPrimaryPrincipal();
        Map map = null;
        try {
            List selectByCustom = (List) superService.selectByCustom("sysUser", "username", principal);
            map = (Map) selectByCustom.get(0);
            Object roleId = map.get("roleId");
            List byCustom = (List) superService.selectByCustom("sysRolePermission", "sysRoleId", (String) roleId);
            for (Object o : byCustom) {
                SysRolePermission sysRolePermission = (SysRolePermission) o;
                List select = (List) superService.selectByCustom("sysPermission", "sysPermissionId", sysRolePermission.getSysPermissionId());
                //授权信息
                permissions.add(((SysPermission) select.get(0)).getPercode());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }


    public void clearCache() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }

}
