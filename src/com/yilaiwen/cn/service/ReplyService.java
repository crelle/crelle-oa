package com.yilaiwen.cn.service;

import java.util.List;

import com.yilaiwen.cn.base.DaoSupport;
import com.yilaiwen.cn.bean.PageBean;
import com.yilaiwen.cn.bean.Reply;
import com.yilaiwen.cn.bean.Topic;

public interface ReplyService extends DaoSupport<Reply>
{
	/**
	 * 查询制定主题中的回复列表  排序：最新的回复排在最后面
	 * @param topic
	 * @return
	 */
	List<Reply> findByTopic(Topic topic);
	
	/**
	 * 查询分页的回复列表数据
	 * @param pageNum
	 * @param topic
	 * @return
	 */
	PageBean getPageBeanByTopic(int pageNum, Topic topic);

}
