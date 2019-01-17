package com.yilaiwen.cn.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.yilaiwen.cn.base.ModelDrivenBaseAction;
import com.yilaiwen.cn.bean.User;

@Controller
@Scope("prototype")
public class LoginoutAction extends ModelDrivenBaseAction<User> {
	
	/**
	 * 登陆页面
	 * @return
	 * @throws Exception
	 */
	public String loginUI() throws Exception {
		return "loginUI";
	}
	/**
	 * 登陆
	 * @return
//	 * @throws Exception
	 */
	public String login() throws Exception {
		User user = userService.findByLoginNameAndPassword(model.getLoginName(),model.getPassword());
		//如果登陆名和密码不正确，就回到登陆页面并提示登录名或者密码不正确
		if(user==null)
		{
			addFieldError("login", "登录名或密码不正确！");
			return "loginUI";
		}
		//如果登陆名与密码都正确，就登陆用户
		else
		{
			ActionContext.getContext().getSession().put("user", user);
			return "toHome";
		}
	}
	/**
	 * 注销
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}
}
