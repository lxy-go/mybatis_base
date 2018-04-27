package com.wdjr.mybatis.dao;

import java.util.List;

import com.wdjr.mybatis.bean.Employee;

public interface EmployeeMapperPlus {

	public Employee getEmpById(Integer id);
	
	public Employee getEmpAndDept(Integer id);
	
	public Employee getEmpByIdStep(Integer id);
	
	public List<Employee> getEmpsByDeptId(Integer deptId);
}
