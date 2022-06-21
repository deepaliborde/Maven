package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Employee;
import com.example.demo.service.Employeeservice;

@Controller


public class employeeController {
	
	@Autowired
	private Employeeservice employeeservice;
	
	// display list of employee
	@GetMapping("/")
	
	public String viewHomepage(Model model) {
		return findPaginated(1, "firstName","asc",model);
		
		
	}
	@GetMapping ("/showNewemployeeForm")
	public String showNewemployeeForm(Model model) {
		Employee employee =new Employee();
		model.addAttribute("employee",employee);
		return "new_employee";
	}
	
	@PostMapping
	public String saveEmployee(@ModelAttribute("employee")Employee employee) {
		employeeservice.saveEmployee(employee);
		return"redirect:/";
	}
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable( value ="id")long id,Model model) {
		
		Employee employee =employeeservice.getEmployeeId(id);
		
		model.addAttribute("employee",employee);
		return "update_employee";
		
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value ="id")long id) {
		this.employeeservice.deleteEmployeeByid(id);
		return "rediect:/";
	
		
	}
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value =" pageNo")int pageNo,
			@RequestParam("sortFieldd")String sortField,
			@RequestParam("sortDir")String sortDir,
			Model model) {
		int pageSize =5;
		
		Page<Employee> page =employeeservice.findPaginated(pageNo,pageSize, sortDir, sortDir);
		List<Employee> listEmployees =page.getContent();
		
		
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("toatlPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());
		model.addAttribute("sortField",sortField);
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("reverseSortDir",sortDir.equals("asc") ?"des" :"asc");
		model.addAttribute("listEmployee",listEmployees);
		return "index";
		
		
		
	}

}
