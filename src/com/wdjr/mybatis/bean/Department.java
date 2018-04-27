package com.wdjr.mybatis.bean;

import java.io.Serializable;
import java.util.List;




public class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String deptName;
	private List<Employee> emps;

	
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Department(Integer id) {
		super();
		this.id = id;


	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public List<Employee> getEmps() {
		return emps;
	}
	public void setEmps(List<Employee> emps) {
		this.emps = emps;
		}
	@Override
	public String toString() {
		return "Department [id=" + id + ", deptName=" + deptName + ", emps=" + emps + "]";
	}

	
	
}
