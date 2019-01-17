package com.yilaiwen.cn.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.jbpm.api.ProcessDefinition;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yilaiwen.cn.bean.User;
import com.yilaiwen.cn.service.DepartmentService;
import com.yilaiwen.cn.service.FlowService;
import com.yilaiwen.cn.service.ForumService;
import com.yilaiwen.cn.service.PrivilegeService;
import com.yilaiwen.cn.service.ProcessDefinitionService;
import com.yilaiwen.cn.service.ReplyService;
import com.yilaiwen.cn.service.RoleService;
import com.yilaiwen.cn.service.TemplateService;
import com.yilaiwen.cn.service.TopicService;
import com.yilaiwen.cn.service.UserService;

public class BaseAction extends ActionSupport
{

	// ===================== 声明Service ====================
	@Resource
	protected RoleService roleService;
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected UserService userService;
	@Resource
	protected PrivilegeService privilegeService;

	@Resource
	protected ForumService forumService;
	@Resource
	protected TopicService topicService;
	@Resource
	protected ReplyService replyService;

	@Resource
	protected ProcessDefinitionService processDefinitionService;
	@Resource
	protected TemplateService templateService;
	@Resource
	protected FlowService flowService;
	
	// ========================= 对分页的支持 =========================

	protected int pageNum = 1; // 当前页，默认为第1页

	public int getPageNum()
	{
		return pageNum;
	}

	public void setPageNum(int pageNum)
	{
		this.pageNum = pageNum;
	}

	// ========================== 工具方法 ==========================

	/**
	 * 获取当前登录的用户
	 */
	public User getCurrentUser()
	{
		return (User) ActionContext.getContext().getSession().get("user");
	}

	/**
	 * 获取客户户的IP地址
	 * 
	 * @return
	 */
	public String getRequestIP()
	{
		return ServletActionContext.getRequest().getRemoteAddr();
	}

	/**
	 * 保存上传的文件，并返回在服务器端真实的存储路径
	 * 
	 * @param upload
	 * @return
	 */
	protected String saveUploadFile(File upload)
	{
		// >> 1, 得到在保存的文件路径的真实地址
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
		String basePath = ServletActionContext.getServletContext().getRealPath(
				"/upload_files");
		String datePath = sdf.format(new Date());

		// >> 2, 如果文件夹不存在，就创建
		File dir = new File(basePath + datePath);
		if (!dir.exists())
		{
			dir.mkdirs();
		}
		String path = basePath + datePath + UUID.randomUUID().toString(); // 注意同名的问题，可以使用uuid做为文件名
		File destFile = new File(path);

		// >> 3, 移动文件
		upload.renameTo(destFile);
		return path;
	}
}
