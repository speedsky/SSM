package com.bestlove.crud.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bestlove.crud.bean.Employee;
import com.bestlove.crud.bean.Msg;
import com.bestlove.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {

		// 使用mybatis分页插件
		PageHelper.startPage(pn, 5);// 页码，每一页大小
		// 使用pageinfo包装信息

		List<Employee> emps = employeeService.getAll();

		PageInfo page = new PageInfo(emps, 5);// 5连续显示的页数

		model.addAttribute("PageInfo", page);
		System.out.println("getEmpsWithJson 成功");
		return Msg.success().add("PageInfo",page);// 来到list页面下
	}
	
	/*
	   /emp/{id} GET     查询员工
	 * /emp      POST    保存员工
	 * /emp/{id} PUT     修改员工
	 * /emp/{id} DELETE  删除员工
	*/
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public  Msg saveEmp(Employee  employee) {
		System.out.println("添加保存"+employee.toString());
		employeeService.saveEmp(employee);
		return Msg.success();
	}
	
	

	/*
	   /emp/{id} GET     查询员工
	 * /emp      POST    保存员工
	 * /emp/{id} PUT     修改员工
	 * /emp/{id} DELETE  删除员工
	*/
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public  Msg getEmp(@PathVariable("id")Integer id) {
		Employee   employee= employeeService.getEmp(id);
		System.out.println("编辑获取"+employee.toString());
		return Msg.success().add("emp", employee);
	}
	
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public  Msg deleteEmp(@PathVariable("ids")String ids) {
		if(ids.contains("-")) {
			List<Integer> del_ids=new ArrayList<Integer>();
			String[] str_ids=ids.split("-");
			for(String str:str_ids) {
				del_ids.add(Integer.parseInt(str));
			}
			employeeService.deleteBatch(del_ids);
			
		}
		else {
			Integer id= Integer.parseInt(ids);
			employeeService.deleteEmp(id);			
		}		
		return Msg.success();
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	public  Msg updateEmp(Employee   employee) {
		System.out.println("编辑获取"+employee.toString());
		employeeService.updateEmp(employee);
		
		return Msg.success();				
	}
	
	
	
	@RequestMapping("/checkuser")
	@ResponseBody
	public  Msg checkuser(String empName) {
		System.out.println("checkuser");
		boolean b=employeeService.checkuser(empName);
		if(b) {
			return Msg.success();
		}
		else {
			return Msg.fail();
		}
	}

	// 处理emps请求
	// @RequestMapping("/emps")
	public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {

		// 使用mybatis分页插件
		PageHelper.startPage(pn, 5);// 页码，每一页大小
		// 使用pageinfo包装信息

		List<Employee> emps = employeeService.getAll();

		PageInfo page = new PageInfo(emps, 5);// 5连续显示的页数
		
		//model.addAttribute("PageInfo", page);

		return "list";// 来到list页面下
	}

}
