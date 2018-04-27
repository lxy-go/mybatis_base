package com.wdjr.mybatis.dao;

import org.apache.ibatis.annotations.Select;

import com.wdjr.mybatis.bean.Employee;

public interface EmployeeMapperAnnotation {

	@Select("select * from tbl_employee where id=#{id}")
	public Employee getEmpById(Integer id);
}
