package com.cskaoyan.shiro;

import com.cskaoyan.domain.authority.SysPermission;
import com.cskaoyan.domain.authority.SysRolePermission;
import com.cskaoyan.domain.vo.SysUserVo;
import com.cskaoyan.service.SuperService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtendFormAuthenticationFilter extends FormAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(FormAuthenticationFilter.class);

    @Autowired
    SuperService superService;

    /**
     * 表示当访问拒绝时
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        if (this.isLoginRequest(request, response)) {
            if (this.isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }

                return this.executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }

                return true;
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the Authentication url [" + this.getLoginUrl() + "]");
            }

            this.saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }

    /**
     * 当登录成功
     *
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        //将user对象放入session
        Map<String, String> params = new HashMap<>();
        params.put("username", token.getPrincipal().toString());

        SysUserVo sysUserVo = new SysUserVo();

        List selectByCustom = (List) superService.selectByCustom("sysUser", "username", token.getPrincipal().toString());
        Map map = (Map) selectByCustom.get(0);
        sysUserVo.setId((String) map.get("id"));
        sysUserVo.setPassword((String) map.get("password"));
        sysUserVo.setUsername((String) map.get("username"));
        sysUserVo.setLocked((String) map.get("locked"));
        sysUserVo.setRolename((String) map.get("roleName"));

        ((HttpServletRequest) request).getSession().setAttribute("activeUser", sysUserVo);

        List selectByCustom2 = (List) superService.selectByCustom("sysUser", "username", (String) map.get("username"));
        map = (Map) selectByCustom2.get(0);
        Object roleId = map.get("roleId");
        List byCustom = (List) superService.selectByCustom("sysRolePermission", "sysRoleId", (String) roleId);
        List sysPermissionList = new ArrayList();
        for (Object o : byCustom) {
            try {
                SysRolePermission sysRolePermission = (SysRolePermission) o;
                List permission = (List) superService.selectByCustom("sysPermission", "sysPermissionId",sysRolePermission.getSysPermissionId());
                sysPermissionList.add(((SysPermission)permission.get(0)).getPercode());
            } catch (Exception e) {
                System.out.println("ExtendFormAuthenticationFilter onLoginSuccess 啊啊啊" + e.getMessage());
            }
        }

        ((HttpServletRequest) request).getSession().setAttribute("sysPermissionList", sysPermissionList);

        if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest
                .getHeader("X-Requested-With"))) {// 不是ajax请求
            issueSuccessRedirect(request, response);
        } else {
            httpServletResponse.setCharacterEncoding("UTF-8");
            PrintWriter out = httpServletResponse.getWriter();
            out.println("{\"success\":true,\"msg\":\"登陆成功！\"}");
            out.flush();
            out.close();
        }
        return false;
    }

    /**
     * 当登录失败
     *
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest
            request, ServletResponse response) {
        if (!"XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request)
                .getHeader("X-Requested-With"))) {// 不是ajax请求
            setFailureAttribute(request, e);
            return true;
        }
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String message = e.getClass().getSimpleName();
            if ("IncorrectCredentialsException".equals(message)) {
                out.println("{\"msg\":\"password_error\"}");
            } else if ("UnknownAccountException".equals(message)) {
                out.println("{\"msg\":\"account_error\"}");
            } else if ("randomCodeError".equals(message)) {
                out.println("{\"msg\":\"randomcode_error\"}");
            } else if ("LockedAccountException".equals(message)) {
                out.println("{\"msg\":\"authentication_error\"}");
            } else {
                out.println("{\"msg\":\"未知错误\"}");
            }
            out.flush();
            out.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return false;
    }
}
