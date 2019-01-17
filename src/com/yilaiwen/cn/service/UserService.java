package com.yilaiwen.cn.service;

import com.yilaiwen.cn.base.DaoSupport;
import com.yilaiwen.cn.bean.User;

public interface UserService extends DaoSupport<User>
{
	/**
	 * 验证用户名和密码，如果用户名和密码正确就返回用户，否则返回null
	 * @param loginName
	 * @param password
	 * @return
	 */
	User findByLoginNameAndPassword(String loginName, String password);

}
