package com.yilaiwen.cn.service;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yilaiwen.cn.base.DaoSupportImpl;
import com.yilaiwen.cn.bean.User;
@Service
@Transactional
@SuppressWarnings("unused")
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService
{

	//默认密码是1234
	@Override
	public void save(User user)
	{
		String md5 = DigestUtils.shaHex("1234");
		user.setPassword(md5);
		getSession().save(user);
	}

	public User findByLoginNameAndPassword(String loginName, String password) {
		String md5 = DigestUtils.md5Hex(password);
		return (User) getSession().createQuery(//
				"from User u where u.loginName=? and u.password=?")//
				.setParameter(0, loginName)//
				.setParameter(1, md5)//
				.uniqueResult();
	}

	
}
