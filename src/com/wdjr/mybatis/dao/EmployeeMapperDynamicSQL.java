package com.wdjr.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wdjr.mybatis.bean.Employee;

public interface EmployeeMapperDynamicSQL {

	//携带了那个字段查询条件就带上这个字段的值
	public List<Employee> getEmpsByConditionIf(Employee employee);
	
	public List<Employee> getEmpsByConditionTrim(Employee employee);
	
	public List<Employee> getEmpsByConditionChoose(Employee employee);	
	
	public List<Employee> getEmpsByConditionForeach(@Param("ids")List<Integer> ids);	
	
	public void updateEmp(Employee employee);

	public void addEmp(@Param("emps")List<Employee> emps);
	
	public List<Employee> getEmpsTestInnerParamter(Employee employee);
}
