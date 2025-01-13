package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}

	public Optional<Employee> getEmployeeById(Long id) {
		return repository.findById(id);
	}

	public Employee saveEmployee(Employee employee) {
		return repository.save(employee);
	}

	public Employee updateEmployee(Long id, Employee updatedEmployee) {
		return repository.findById(id).map(employee -> {
			employee.setName(updatedEmployee.getName());
			employee.setEmail(updatedEmployee.getEmail());
			employee.setDepartment(updatedEmployee.getDepartment());
			return repository.save(employee);
		}).orElseThrow(() -> new RuntimeException("Employee not found"));
	}

	public void deleteEmployee(Long id) {
		repository.deleteById(id);
	}
}
