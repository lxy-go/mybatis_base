package com.wdjr.mybatis.dao;


import java.util.Map;

import org.apache.ibatis.annotations.MapKey;

import com.wdjr.mybatis.bean.Employee;

public interface EmployeeMapper {

	//多条记录封装map:Map<Integer ,Employee> :键时记录的主键，值是记录后的javaBean
	//告诉mybatis封装这个map，使用哪个属性作为map的key
	@MapKey("id")
	public Map<Integer,Employee> getEmpByLastNameLikeReturnMap(String lastName);
	
	public Map<String,Object> getEmpByIdReturnMap(Integer id);
	public void addEmp(Employee emp);
	public Boolean deletEmpById(Integer id);
	public Boolean updateEmp(Employee emp);
	public Employee getEmpById(Integer id);
	
	public Employee getEmpByIdAndLastName(Integer id,String LastName);

	
}
