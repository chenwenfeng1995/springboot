package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.user.entity.Emp;
import com.user.service.IEmpService;

@Controller
@RequestMapping("emp")
public class EmpController {

	@Autowired
	private IEmpService empService;
	
	@RequestMapping("findall")
	public String findall(Model model) {
		List<Emp> empList =empService.findallemp();
		for (Emp emp : empList) {
			System.out.println(emp.toString());
		}
		model.addAttribute("empList", empList);
		return "index";
	}
}
