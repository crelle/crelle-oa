package com.yilaiwen.cn;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yilaiwen.cn.action.TestAction;

public class TestSpring {
	private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	//测试sessionFactory
	@Test
	public void testSessionFacroty()
	{
		SessionFactory sf = (SessionFactory) ac.getBean("sessionFactory");
		System.out.println(sf.openSession());
	}
//	//测试事务管理      
//	@Test
//	public void testTx() throws Exception
//	{
//		TestService service =(TestService) ac.getBean("testService");
//		service.saveTwoUser();
//	}
//	
//	//测试TestAction的管理
//	@Test
//	public void testAction() throws Exception
//	{
//		TestAction ta = (TestAction) ac.getBean("testAction");
//		System.out.println(ta);
//	}
}
