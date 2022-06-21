package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.model.Employee;

public interface Employeeservice {
	List<Employee> getAllEmployees();
	void saveEmployee(Employee employee);
	Employee getEmployeeId(long id);
	Employee getEmployeeById(long id);
	void deleteEmployeeByid(long id);
	Page<Employee> findPaginated(int pageNO ,int pageSize ,String sortField, String sortDirection);

}
