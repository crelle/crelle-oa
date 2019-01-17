package com.yilaiwen.cn.action;


import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.opensymphony.xwork2.ActionContext;
import com.yilaiwen.cn.base.ModelDrivenBaseAction;
import com.yilaiwen.cn.bean.Forum;
import com.yilaiwen.cn.bean.PageBean;
import com.yilaiwen.cn.bean.Reply;
import com.yilaiwen.cn.bean.Topic;

@Controller
@Scope("prototype")
public class TopicAction extends ModelDrivenBaseAction<Topic> {

	private Long forumId;

	/** 显示单个主题 */
	public String show() throws Exception {
		// 准备数据: topic
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);

		// 准备数据: replyList
//		List<Reply> replyList = replyService.findByTopic(topic);
//		ActionContext.getContext().put("replyList", replyList);
		//准备分页的数据
		PageBean pageBean = replyService.getPageBeanByTopic(pageNum,topic);
		ActionContext.getContext().getValueStack().push(pageBean);

		return "show";
	}

	/** 发新帖页面 */
	public String addUI() throws Exception {
		// 准备数据
		Forum forum = forumService.getById(forumId);
		ActionContext.getContext().put("forum", forum);
		return "addUI";
	}

	/** 发新帖 */
	public String add() throws Exception {
		// 封装对象
		Topic topic = new Topic();
		// >> a, 表单中的参数
		topic.setTitle(model.getTitle());
		topic.setContent(model.getContent());
		topic.setForum(forumService.getById(forumId));
		// >> b, 在显示层才能获得的数据
		topic.setAuthor(getCurrentUser()); // 当前登录的用户
		topic.setIpAddr(getRequestIP()); // 客户端的IP地址

		// 调用业务方法
		topicService.save(topic);

		ActionContext.getContext().put("topicId", topic.getId());
		return "toShow"; // 转到当前这个新主题的显示页面
	}

	// ---

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

}