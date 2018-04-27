package com.wdjr.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.wdjr.mybatis.bean.Department;
import com.wdjr.mybatis.bean.Employee;
import com.wdjr.mybatis.dao.DepartmentMapper;
import com.wdjr.mybatis.dao.EmployeeMapper;
import com.wdjr.mybatis.dao.EmployeeMapperAnnotation;
import com.wdjr.mybatis.dao.EmployeeMapperPlus;

/**
 * 1.接口式编程
 * 原生：                Dao ==>DaoImp
 * 
 * mybatis  Mapper ==>xxMapper.xml 
 * 2.SqlSession代表和数据库一次性会话：用完关闭
 * 
 * 3.SqlSession和connection一样都是非线程安全，每次引用应该获取新的对象，不应该共享的成员变量中
 * 4.mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象
 * (将接口和xml绑定)
 * 5.两个重要的配置文件
 *   mybatis的全局配置文件：含数据库的连接池信息，事务管理信息 ，系统环境运行信息
 *   sql映射文件：保存了每一个sql语句的映射信息
 * 
 * @author Lion.Lee
 *
 * @data 2018年4月20日 下午4:10:28
 */

public class MybatisTest {

	
	/**
	 * 1.根据xml文件配置，创建sqlSessionFactory对象
	 * 2.获取sqlSession实例，执行已经映射的sql语句
	 * 3.使用sql的唯一标识，告诉Mybatis来执行那句sql
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
		
		// 唯一标识：Unique identifier matching the statement to use.
		//sql的条件：A parameter object to pass to the statement.
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			Employee emp = openSession.selectOne("com.wdjr.mybatis.EmployeeMapper.selectEmp",1);
			System.out.println(emp);
		} finally {
			// 关闭Session
			openSession.close();
		}	
	}
	
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
		
		//3.获取接口的实现类对象
		//4.会为接口自动的创建代理对象，代理对象去执行 增删改查
		EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
		
		Employee employee = mapper.getEmpById(1);
		
		System.out.println(mapper.getClass());
		
		System.out.println(employee);
	}
	
	@Test
	public void test02() throws IOException {
		//1.获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		
		//2.获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		//注入基于注解的类
		EmployeeMapperAnnotation mapper = openSession.getMapper(EmployeeMapperAnnotation.class);
		Employee emp = mapper.getEmpById(1);
		System.out.println(emp);
		
	}
	/**
	 * 增删改查测试
	 * 允许增删改允许以下类型的返回值
	 * 	Integer Long Boolean
	 * SqlSession openSession = sqlSessionFactory.openSession(true);==>自动提交
	 * @throws IOException
	 */
	@Test
	public void test03() throws IOException {
		//1.获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		
		//2.获取sqlSession对象(获取到的不会自动提交数据)
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			/*
			//1.添加员工
			Employee emp = new Employee(null,"test01","test01@163.com","0");
			mapper.addEmp(emp);
			System.out.println(emp.getId());*/
			//2.修改员工
//			Employee emp = new Employee(1,"LionLee","jerry@163.com","1");
//			Boolean updateEmp = mapper.updateEmp(emp);
//			System.out.println(updateEmp);
			//3.删除
			//mapper.deletEmpById(3);
			//手动提交数据
//			Map<String, Object> empByIdReturnMap = mapper.getEmpByIdReturnMap(1);
//			System.out.println(empByIdReturnMap);
			Map<Integer, Employee> empByLastNameLikeReturnMap = mapper.getEmpByLastNameLikeReturnMap("%r%");
			
			System.out.println(empByLastNameLikeReturnMap);
			openSession.commit();
		} finally {
			openSession.close();
		}
		
		
	}
	@Test
	public void test04() throws IOException {
		//1.获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		
		//2.获取sqlSession对象(获取到的不会自动提交数据)
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			
			EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
//			Employee emp = mapper.getEmpById(1);
			Employee empAndDept = mapper.getEmpAndDept(1);
			System.out.println(empAndDept);
			System.out.println(empAndDept.getDept());
			
			openSession.commit();
		}finally {
			openSession.close();
		}
	}
	@Test
	public void test05() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		
		//2.获取sqlSession对象(获取到的不会自动提交数据)
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
			Department dept = mapper.getDeptById(1);
			
			System.out.println(dept);
		} finally {
			openSession.close();
		}
	}
	
	@Test
	public void test06() throws IOException {
		
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		
		//2.获取sqlSession对象(获取到的不会自动提交数据)
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
			Employee emp = mapper.getEmpByIdStep(1);
			System.out.println("++++++++");
		
		} finally {
			openSession.close();
		}
	}
	@Test
	public void test07() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		
		//2.获取sqlSession对象(获取到的不会自动提交数据)
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
			Department dept = mapper.getDeptByIdPlus(1);
			System.out.println(dept);
			
		} finally {
			openSession.close();
		}
	}
	@Test
	public void test08() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		
		//2.获取sqlSession对象(获取到的不会自动提交数据)
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
			Department dept = mapper.getDeptByIdStep(1);
			System.out.println(dept);
			
		} finally {
			openSession.close();
		}
	}
	
}
