package com.yilaiwen.cn.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.yilaiwen.cn.base.ModelDrivenBaseAction;
import com.yilaiwen.cn.bean.Forum;
import com.yilaiwen.cn.bean.PageBean;
import com.yilaiwen.cn.bean.Topic;
import com.yilaiwen.cn.service.ForumService;

@Controller
@Scope("prototype")
public class ForumAction extends ModelDrivenBaseAction<Forum>
{
	private int pageNum = 1;//当前页
	/**
	 * 版块列表
	 */
	public String list() throws Exception{
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	}
	
	/**
	 * 显示单个版块
	 */
	public String show() throws Exception{
		//准备数据：Forum
		Forum forum = forumService.getById(model.getId());
		ActionContext.getContext().put("forum", forum);
		//准备分页的数据
		PageBean pagebean = topicService.getPageBeanByForum(pageNum,forum);
		ActionContext.getContext().getValueStack().push(pagebean);//放到栈顶
		
		return "show";
	}

	public int getPageNum()
	{
		return pageNum;
	}

	public void setPageNum(int pageNum)
	{
		this.pageNum = pageNum;
	}
	
	
}
