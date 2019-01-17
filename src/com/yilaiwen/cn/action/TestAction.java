package com.yilaiwen.cn.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.yilaiwen.cn.TestService;


@Controller
public class TestAction extends ActionSupport {
	@Resource
	private TestService testservice;
	@Override
	public String execute() throws Exception {
		System.out.println(">>>>>>>>>>>>>>TestAction.execute()");
		System.out.println(">>>>>>>>>>>>testsetvice="+testservice);
		testservice.saveTwoUser();
		return "success";
	}
	

}
