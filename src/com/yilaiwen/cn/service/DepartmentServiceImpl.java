package com.yilaiwen.cn.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yilaiwen.cn.base.DaoSupportImpl;
import com.yilaiwen.cn.bean.Department;
@Service
@SuppressWarnings("unchecked")
public class DepartmentServiceImpl extends DaoSupportImpl<Department> implements DepartmentService
{
	

	/**
	 * 查询所有顶级部门
	 */

	public List<Department> findTopList()
	{
		return getSession().createQuery(//
				"from Department d where d.parent is null")//
				.list();
	}
	/**
	 * 查询指定顶级部门列表
	 */
	public List<Department> findChildren(Long parentId)
	{
		return getSession().createQuery(//
				"from Department d where d.parent.id=?")//
				.setParameter(0, parentId)//
				.list();
	}

}
