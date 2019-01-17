package com.yilaiwen.cn.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.yilaiwen.cn.base.ModelDrivenBaseAction;
import com.yilaiwen.cn.bean.Reply;
import com.yilaiwen.cn.bean.Topic;

@Controller
@Scope("prototype")
public class ReplyAction extends ModelDrivenBaseAction<Reply>
{
	private Long topicId;
	/**
	 * 发表回复页面
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception{
		//准备数据
		Topic topic = topicService.getById(topicId);
		ActionContext.getContext().put("topic", topic);
		return "addUI";
	}
	
	/**
	 * 发表回复功能
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		
		// 封装对象
		Reply reply = new Reply();
		// a, 表单中的参数
		reply.setContent(model.getContent());
		reply.setTopic(topicService.getById(topicId));
		// b, 在显示层才能获得的数据
		reply.setAuthor(getCurrentUser()); // 当前登录的用户
		reply.setIpAddr(getRequestIP()); // 客户端的IP地址

		// 调用业务方法
		replyService.save(reply);

		return "toTopicShow"; // 转到当前这个新回复所属的主题显示页面
	}

	public Long getTopicId()
	{
		return topicId;
	}

	public void setTopicId(Long topicId)
	{
		this.topicId = topicId;
	}
	
}
