package com.bestlove.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bestlove.crud.bean.DepartmentExample.Criteria;
import com.bestlove.crud.bean.Employee;
import com.bestlove.crud.bean.EmployeeExample;
import com.bestlove.crud.dao.EmployeeMapper;

@Service
public class EmployeeService {

	@Autowired
	EmployeeMapper employeeMapper;
	
	public  List<Employee> getAll(){	
		
		return employeeMapper.selectByExampleWithDept(null);
	}

	public void saveEmp(Employee employee) {
		
		employeeMapper.insertSelective(employee);
	}

	public boolean checkuser(String empName) {		
		EmployeeExample employeeExample =new EmployeeExample();
		EmployeeExample.Criteria criteria = employeeExample.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count= employeeMapper.countByExample(employeeExample);
		return count==0;
	}

	public Employee getEmp(Integer id) {
		
		return    employeeMapper.selectByPrimaryKey(id);
	}

	public Integer deleteEmp(Integer id) {
		
		return  employeeMapper.deleteByPrimaryKey(id);
	}

	public Integer updateEmp(Employee employee) {		
		
		return employeeMapper.updateByPrimaryKeySelective(employee);
	}

	public void deleteBatch(List<Integer> ids) {
		EmployeeExample example	=new EmployeeExample();
		EmployeeExample.Criteria criteria =example.createCriteria();
		criteria.andEmpIdIn(ids);
		// TODO Auto-generated method stub
		employeeMapper.deleteByExample(example);
	}

	
}
