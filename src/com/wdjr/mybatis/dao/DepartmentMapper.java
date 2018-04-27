package com.wdjr.mybatis.dao;

import com.wdjr.mybatis.bean.Department;

public interface DepartmentMapper {

	public Department getDeptById(Integer id);
	//根据部门查出在此部门下的所有员工
	public Department getDeptByIdPlus(Integer id);
	//分步查询
	public Department getDeptByIdStep(Integer id);
}
