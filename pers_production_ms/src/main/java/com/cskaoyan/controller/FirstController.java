package com.cskaoyan.controller;

import com.cskaoyan.domain.authority.SysPermission;
import com.cskaoyan.domain.customize.ActiveUser;
import com.cskaoyan.service.SysService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class FirstController {

	@Autowired
	private SysService sysService;

	//跳转登录
	@RequestMapping(value={"/","/first"})
	public String first(Model model)throws Exception{
		System.out.println("bbbbbbbbbbb");
		return "login";
	}

	//首页
	@RequestMapping(value={"/home"})
	public String home(HttpSession session, Model model)throws Exception{

		//从shiro的session中取activeUser
		Subject subject = SecurityUtils.getSubject();
		//取身份信息
		ActiveUser activeUser = (ActiveUser) subject.getPrincipal();

		List<SysPermission> permissionList = null;

		try {
			permissionList = sysService.findPermissionListByUserId(activeUser.getUserid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<String> sysPermissionList = new ArrayList<String>();
		if(permissionList != null){
			for(int i=0;i<permissionList.size();i++){
				sysPermissionList.add(permissionList.get(i).getPercode());
			}
		}

		//通过model传到页面
		model.addAttribute("activeUser", activeUser);
		/*model.addAttribute("sysPermissionsList", sysPermissionsList);*/

		//session
		session.setAttribute("sysPermissionList", sysPermissionList);

		return "home";
	}
}
