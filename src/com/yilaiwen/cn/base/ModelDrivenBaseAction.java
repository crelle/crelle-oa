package com.yilaiwen.cn.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yilaiwen.cn.bean.Department;
import com.yilaiwen.cn.bean.User;
import com.yilaiwen.cn.service.DepartmentService;
import com.yilaiwen.cn.service.ForumService;
import com.yilaiwen.cn.service.PrivilegeService;
import com.yilaiwen.cn.service.ProcessDefinitionService;
import com.yilaiwen.cn.service.ReplyService;
import com.yilaiwen.cn.service.RoleService;
import com.yilaiwen.cn.service.TopicService;
import com.yilaiwen.cn.service.UserService;

public class ModelDrivenBaseAction<T> extends BaseAction implements ModelDriven<T> 
{
	
	
	
	//======对ModelDriver的支持==============
	protected T model;
	public ModelDrivenBaseAction()
	{
		try{
		//通过反射获取T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
		//通过反射创建model的实例
		model = clazz.newInstance();
		}
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}
	}
	public T getModel()
	{
		return model;
	}
	
	
}
