package com.yilaiwen.cn.action;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.yilaiwen.cn.base.ModelDrivenBaseAction;
import com.yilaiwen.cn.bean.Department;
import com.yilaiwen.cn.bean.Role;
import com.yilaiwen.cn.bean.User;
import com.yilaiwen.cn.util.DepartmentUtils;
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class UserAction extends ModelDrivenBaseAction<User>
{
	private Long departmentId;
	private Long[] roleIds;
	/**
	 * 列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception
	{
		List<User> userList = userService.findAll();
		ActionContext.getContext().put("userList", userList);

		return "list";
	}
	/**
	 * 删除
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception
	{
		userService.delete(model.getId());
		return "toList";
	}
	/**
	 * 增加页面
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception
	{
		// 准备数据：departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartmentList(topList, null);
		ActionContext.getContext().put("departmentList", departmentList);

		// 准备数据：roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		return "saveUI";
	}
	/**
	 * 增加
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception
	{
		// 封装对象
		// >> 处理关联的一个部门
		model.setDepartment(departmentService.getById(departmentId));
		// >> 处理关联的多个岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));

		// 保存到数据库
		userService.save(model);

		return "toList";
	}
	
	/**
	 * 编辑页面
	 * @return
	 * @throws Exception
	 */
	public String editUI() throws Exception
	{
		// 准备回显的数据
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		// >> 处理部门
		if (user.getDepartment() != null) {
			departmentId = user.getDepartment().getId();
		}
		// >> 处理岗位
		roleIds = new Long[user.getRoles().size()];
		int index = 0;
		for (Role role : user.getRoles()) {
			roleIds[index++] = role.getId();
		}

		// 准备数据：departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartmentList(topList, null);
		ActionContext.getContext().put("departmentList", departmentList);

		// 准备数据：roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		return "saveUI";
	}
	/**
	 * 编辑
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception
	{
		// 1，从数据库中取出原对象
		User user = userService.getById(model.getId());

		// 2，设置要修改的属性
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		// >> 处理关联的一个部门
		user.setDepartment(departmentService.getById(departmentId));
		// >> 处理关联的多个岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));

		// 3，更新到数据库
		userService.update(user);

		return "toList";
	}
	
	/**
	 * 初始化密码
	 * @return
	 * @throws Exception
	 */
	public String initpassword() throws Exception
	{
				// 1，从数据库中取出原对象
				User user = userService.getById(model.getId());
				String md5 = DigestUtils.shaHex("1234");
				// 2，设置要修改的属性
				user.setPassword(md5);
				// 3，更新到数据库
				userService.update(user);

				return "toList";
	}
	public Long getDepartmentId()
	{
		return departmentId;
	}
	public void setDepartmentId(Long departmentId)
	{
		this.departmentId = departmentId;
	}
	public Long[] getRoleIds()
	{
		return roleIds;
	}
	public void setRoleIds(Long[] roleIds)
	{
		this.roleIds = roleIds;
	}
	
	
}
