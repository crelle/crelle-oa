package com.yilaiwen.cn.action;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yilaiwen.cn.base.ModelDrivenBaseAction;
import com.yilaiwen.cn.bean.Privilege;
import com.yilaiwen.cn.bean.Role;
import com.yilaiwen.cn.service.RoleService;

@Controller
@Scope("prototype")
public class RoleAction extends ModelDrivenBaseAction<Role>
{	

	private Long[] privilegeIds;
 	/*列表*/
	public String list() throws Exception
	{
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "list";
	}
	/*删除*/
	public String delete() throws Exception
	{	
		roleService.delete(model.getId());
		return "toList";
	}
	/*添加页面*/
	public String addUI() throws Exception
	{
		return "saveUI";
	}
	/*添加*/
	public String add() throws Exception
	{
		//封装对象
//		Role role = new Role();
//		role.setName(model.getName());
//		role.setDescription(model.getDescription());	
		//保存到数据库
		roleService.save(model);
		return "toList";
	}
	/*修改页面*/
	public String editUI() throws Exception
	{
		//准备要回显的数据
		Role role = roleService.getById(model.getId());
		
		ActionContext.getContext().getValueStack().push(role);
//		model.setName(role.getName());
//		model.setDescription(role.getDescription());
		return "saveUI";
	}
	/*修改*/
	public String edit() throws Exception
	{	
		//从数据库中获取要修改的原始对象
		Role role = roleService.getById(model.getId());
		//设置要修改的属性
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		//更新到数据库
		roleService.update(role);
		return "toList";
	}
	/*设置权限页面*/
	public String setPrivilegeUI() throws Exception {
		// 准备数据
		List<Privilege> topPrivilegeList = privilegeService.findTopList();
		ActionContext.getContext().put("topPrivilegeList", topPrivilegeList);

		// 准备要回显的数据
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);

		// 准备回显的权限数据
		privilegeIds = new Long[role.getPrivileges().size()];
		int index = 0;
		for (Privilege p : role.getPrivileges()) {
			privilegeIds[index++] = p.getId();
		}

		return "setPrivilegeUI";
	}
	/*设置权限*/
	public String setPrivilege() throws Exception
	{	
		//1.从数据库中获取要修改的原始页面
		Role role = roleService.getById(model.getId());
		//2.设置要修改的属性
		List<Privilege> privilegeList = privilegeService.getByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privilegeList));
		//3.更新数据库
		roleService.update(role);
		return "toList";
	}
	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}
	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
	
	
}
