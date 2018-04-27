package com.wdjr.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.wdjr.mybatis.bean.Employee;
import com.wdjr.mybatis.dao.EmployeeMapper;

public class CacheTest {

	public SqlSessionFactory getSqlSessionFactory() throws IOException{
		
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	/**
	 * 两级缓存
	 * 一级缓存（本地缓存）
	 * 	         与数据库同一次会话期间查询到的数据会放到本地缓存中
	 *     如果以后有相同的数据直接从缓存中拿，没必要查询数据库
	 * 二级缓存（全局缓存）
	 * @throws IOException 
	 */
	@Test
	public void testFirstLevelCache() throws IOException {
		
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			Employee emp = mapper.getEmpById(1);
			openSession.clearCache();
			System.out.println(emp);
			Employee emp2 = mapper.getEmpById(1);
			System.out.println(emp2);
		} finally {
			openSession.close();
		}
	}
	@Test
	public void testSecondLevelCache() throws IOException {
		
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		SqlSession openSession2 = sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			EmployeeMapper mapper2 = openSession2.getMapper(EmployeeMapper.class);
			Employee emp = mapper.getEmpById(1);
			System.out.println(emp);
			openSession.close();
			Employee emp2 = mapper2.getEmpById(1);
			System.out.println(emp2);
			openSession2.close();	
		} finally {
			
		}
	}
}
