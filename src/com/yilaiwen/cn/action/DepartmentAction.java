package com.yilaiwen.cn.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yilaiwen.cn.base.ModelDrivenBaseAction;
import com.yilaiwen.cn.bean.Department;
import com.yilaiwen.cn.service.DepartmentService;
import com.yilaiwen.cn.util.DepartmentUtils;

@Controller
@Scope("prototype")
public class DepartmentAction extends ModelDrivenBaseAction<Department> 
{

	private Long parentId;


	/**
	 * 列表:列表页面只显示一层的部门数据（同级别），默认显示最顶级的部门列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception
	{
		List<Department> departmentList = null;
		if(parentId==null)
		{
			//默认显示顶级部门列表
			departmentList=departmentService.findTopList();
		}
		else
		{
			//显示制定部门的子部门列表
			departmentList=departmentService.findChildren(parentId);
			Department parent = departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}
	/**
	 * 删除
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception
	{
		departmentService.delete(model.getId());
		return"toList";
	} 
	/**
	 * 增加
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception
	{
		Department parent = departmentService.getById(parentId);
		model.setParent(parent);
		departmentService.save(model);
		return"toList";
	} 
	/**
	 * 增加UI
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception
	{
//		List<Department> departmentList = departmentService.findAll();
//		ActionContext.getContext().put("departmentList", departmentList);
		//准备数据
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList=DepartmentUtils.getAllDepartmentList(topList,null);
		ActionContext.getContext().put("departmentList", departmentList);

		return"saveUI";
	}
	/**
	 * 编辑
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception
	{
		//1.从数据库中取出要修改的原始数据
		Department department = departmentService.getById(model.getId());
		//2.设置要修改的属性
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		//3.处理上级部门
		Department parent = departmentService.getById(parentId);
		department.setParent(parent);
		//4.更新到数据库
		departmentService.update(department);
		return"toList";
	} 
	/**
	 * 编辑UI
	 * @return
	 * @throws Exception
	 */
	public String editUI() throws Exception
	{
		//准备回显的数据
				Department department = departmentService.getById(model.getId());//当前要修改的部门
				ActionContext.getContext().getValueStack().push(department);
				if(department.getParent()!=null)
				{
					this.parentId=department.getParent().getId();
				}
		//准备数据
//				List<Department> departmentList = departmentService.findAll();
//				ActionContext.getContext().put("departmentList", departmentList);
				List<Department> topList = departmentService.findTopList();
				List<Department> departmentList=DepartmentUtils.getAllDepartmentList(topList,department);
				ActionContext.getContext().put("departmentList", departmentList);
		return"saveUI";
	}
	public Long getParentId()
	{
		return parentId;
	}
	public void setParentId(Long parentId)
	{
		this.parentId = parentId;
	} 

	
}
