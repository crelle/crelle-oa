package com.yilaiwen.cn.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yilaiwen.cn.base.DaoSupportImpl;
import com.yilaiwen.cn.bean.Privilege;

@Service
@Transactional 
@SuppressWarnings("unchecked")
public class PrivilegeServiceImpl extends DaoSupportImpl<Privilege> implements PrivilegeService {
	/**
	 * 查询所有的权限的url的集合
	 */
	public List<String> getAllPrivilegeUrls()
	{
		return getSession().createQuery(//
		"SELECT DISTINCT p.url FROM Privilege p WHERE p.url IS NOT NULL")//
		.list();
	}
	/**
	 * 查询所有的顶级的权限的集合
	 */
	public List<Privilege> findTopList() {
		return getSession().createQuery(//
				"From Privilege p where p.parent is null")//
				.list();
	}
	
}
