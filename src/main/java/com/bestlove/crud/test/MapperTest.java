package com.bestlove.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bestlove.crud.bean.Department;
import com.bestlove.crud.bean.Employee;
import com.bestlove.crud.dao.DepartmentMapper;
import com.bestlove.crud.dao.EmployeeMapper;





/**
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MapperTest {
	
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	SqlSession sqlSession;
	
	@Test
	public void testCRUD() {
		
		System.out.println(departmentMapper);	
		
		System.out.println(departmentMapper.insertSelective(new Department(null,"开发部")));
		System.out.println(departmentMapper.insertSelective(new Department(null,"市场部")));
		System.out.println(departmentMapper.insertSelective(new Department(null,"测试部")));
		
		System.out.println(employeeMapper);	

		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for(int i=0;i<1000;i++) {
			String uid=UUID.randomUUID().toString().substring(0,5);
			
			String gen= i%2==0?"M":"F";
			
			mapper.insertSelective(new Employee(null,uid,gen,uid+"@163.com",i % 3+1));
			
		}
		
		System.out.println("OVER");	

	}
	
	
	
	
	

}
