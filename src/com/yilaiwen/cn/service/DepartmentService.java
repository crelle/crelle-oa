package com.yilaiwen.cn.service;

import java.util.List;

import com.yilaiwen.cn.base.DaoSupport;
import com.yilaiwen.cn.bean.Department;

public interface DepartmentService extends DaoSupport<Department>
{
	
	/**
	 * 查询所有顶级部门
	 * @return
	 */
	List<Department> findTopList();
	/**
	 * 查询制定顶级部门的所有部门
	 * @param parentId
	 * @return
	 */
	List<Department> findChildren(Long parentId);

}
