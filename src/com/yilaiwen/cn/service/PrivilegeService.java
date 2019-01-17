package com.yilaiwen.cn.service;

import java.util.List;

import com.yilaiwen.cn.base.DaoSupport;
import com.yilaiwen.cn.bean.Privilege;
public interface PrivilegeService extends DaoSupport<Privilege> {
	/**
	 * 查询所有顶级的权限列表
	 * @return
	 */
	List<Privilege> findTopList();
	/**
	 * 查询所有的权限的url的集合（不包含空值并且不能重复）
	 * @return
	 */
	List<String> getAllPrivilegeUrls();

}
