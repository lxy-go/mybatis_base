package com.wdjr.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.wdjr.mybatis.bean.Employee;


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
}
