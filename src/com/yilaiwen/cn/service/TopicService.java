package com.yilaiwen.cn.service;

import java.util.List;

import com.yilaiwen.cn.base.DaoSupport;
import com.yilaiwen.cn.bean.Forum;
import com.yilaiwen.cn.bean.PageBean;
import com.yilaiwen.cn.bean.Topic;

public interface TopicService extends DaoSupport<Topic>
{
	/**
	 * 查询指定板块中的主题列表。排序：最新状态在最上面，置顶帖在最上面。
	 * @param forum
	 * @return
	 */
	List<Topic> findByForum(Forum forum);
	/**
	 * 查询分页的主题列表数据
	 * 
	 * @param pageNum
	 * @param forum
	 * @return
	 */
	@Deprecated
	PageBean getPageBeanByForum(int pageNum, Forum forum);
	
}
