package com.paypal.bfs.test.employee.service;

import java.util.List;

import com.paypal.bfs.test.employee.entity.Employee;
import com.paypal.bfs.test.employee.exception.RecordNotFoundException;

public interface EmployeeService {
	public List<Employee> getAllEmployees();

	public Employee getEmployeeById(Integer id) throws RecordNotFoundException;

	public Employee createEmployee(Employee employee);

	public String deleteEmployeeById(Integer id) throws RecordNotFoundException;
}
