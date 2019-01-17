package com.yilaiwen.cn.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.yilaiwen.cn.bean.Department;

public class DepartmentUtils
{
	/**
	 * 遍历部门数，把所有部门改掉后放到同一个List中返回，通过名称中的空格表示层次
	 * @param topList
	 * @param removeDepartment 这个部门的子孙部门都不要,如果为null表示没有要移除的部门分支
	 * @return
	 */
	public static List<Department> getAllDepartmentList(List<Department> topList,Department removeDepartment)
	{
		List<Department> list = new ArrayList<Department>();
		walkTree(topList,"┢",list,removeDepartment);
		return list;
	}
	//递归遍历
	private static void walkTree (Collection<Department> topList,String prefix,List<Department> list,Department removeDepartment)
	{
		for(Department top:topList)
		{
			//去掉指定部门分支
			if(removeDepartment!=null && top.getId().equals(removeDepartment.getId()))
			{
				continue;
			}
			//顶点
			Department copy =new Department();//不要使用Session缓存中的对象，最好使用副本
			copy.setId(top.getId());
			copy.setName(prefix+top.getName());
			list.add(copy);
			//子树
			walkTree(top.getChildren(),"　"+prefix,list,removeDepartment);
		}
	}

}
