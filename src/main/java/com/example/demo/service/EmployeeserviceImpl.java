package com.example.demo.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeserviceImpl implements Employeeservice {
	@Autowired

	private EmployeeRepository employeeRepository;
	private Object employeeRespository;

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();


	}

	@SuppressWarnings("unchecked")
	public void saveEmployee(Employee employee) {
		this.employeeRepository.save(employee);

	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> optional = employeeRepository.findById(id);

		Employee employee =null;
		if(optional.isPresent()) {
			employee =optional.get();
		}else {
			throw new RuntimeException("employee not found for Id ::"+id);

		}
		return employee;
	}


	@Override
	public void deleteEmployeeByid(long id) {
		this.employeeRepository.deleteById(id);


	}

	@Override
	public Employee getEmployeeId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Employee> findPaginated(int pageNO, int pageSize,String sortField, String sortDirection){
		Sort sort =sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		Pageable pageable =PageRequest.of(pageNO -1, pageSize);
		return this.employeeRepository.findAll(pageable);
	}

	
}