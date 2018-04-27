package com.wdjr.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.wdjr.mybatis.bean.Department;
import com.wdjr.mybatis.bean.Employee;
import com.wdjr.mybatis.dao.EmployeeMapperDynamicSQL;

public class DynamicSqlTest {

	public SqlSessionFactory getSqlSessionFactory() throws IOException{
		
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	@Test
	public void test01() throws IOException {
		//1.获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		
		//2.获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
			Employee emp = new Employee(null, "%L%", "jerry@163.com", null, null, null);
			//List<Employee> emps = mapper.getEmpsByConditionIf(emp);
			//List<Employee> emps2 = mapper.getEmpsByConditionTrim(emp);
			List<Employee> emps3 = mapper.getEmpsByConditionChoose(emp);
			System.out.println(emps3);
			
		} finally {
			openSession.close();
		}
		
	
	}
	@Test
	public void test02() throws IOException {
		//1.获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		
		//2.获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
			
			
			List<Employee> empsByConditionForeach = mapper.getEmpsByConditionForeach(Arrays.asList(1,2,3,4));
			//mapper.updateEmp(emp);
			//openSession.commit();
		} finally {
			openSession.close();
			
		}
	}
	@Test
	public void testBatchSave() throws IOException {
		//1.获取sqlSessionFactory对象
			SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
			
			//2.获取sqlSession对象
			SqlSession openSession = sqlSessionFactory.openSession();
			try {
				EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
				List<Employee> emps=new ArrayList<>();
				emps.add(new Employee(null, "hefeng", "hefeng@qq.com", "1", 2, new Department(1)));
				emps.add(new Employee(null, "lizhen", "lizhen@qq.com", "0", 1, new Department(1)));
				mapper.addEmp(emps);
				openSession.commit();
			} finally {
				openSession.close();
			}
	}
	@Test
	public void testInner() throws IOException {
		//1.获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		
		//2.获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
			
			//Employee emp = new Employee(null, "smith", "smith@qq.com", "1", 1, new Department(1));
			Employee emp=new Employee();
			emp.setLastName("L");
			mapper.getEmpsTestInnerParamter(emp);
			openSession.commit();
		} finally {
			openSession.close();
		}
	}
}
