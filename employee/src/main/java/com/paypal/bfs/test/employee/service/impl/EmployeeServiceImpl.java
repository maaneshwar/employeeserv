package com.paypal.bfs.test.employee.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.bfs.test.employee.entity.Employee;
import com.paypal.bfs.test.employee.exception.RecordNotFoundException;
import com.paypal.bfs.test.employee.repository.EmployeeRepository;
import com.paypal.bfs.test.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employeeList = employeeRepository.findAll();

		if (!employeeList.isEmpty()) {
			return employeeList;
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public Employee getEmployeeById(Integer id) throws RecordNotFoundException {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			return employee.get();
		} else {
			throw new RecordNotFoundException("No employee record exist for the id " + id);
		}
	}

	@Override
	public Employee createEmployee(Employee emp) {
		Optional<Employee> employee = employeeRepository.findById(emp.getId());

		if (employee.isPresent()) {
			Employee existEmployee = employee.get();
			existEmployee.setFirstName(emp.getFirstName());
			existEmployee.setLastName(emp.getLastName());
			existEmployee.setEmail(emp.getEmail());
			existEmployee.setDob(emp.getDob());
			existEmployee.setAddress(emp.getAddress());

			existEmployee = employeeRepository.save(existEmployee);

			return existEmployee;
		} else {
			emp = employeeRepository.save(emp);

			return emp;
		}
	}

	@Override
	public String deleteEmployeeById(Integer id) throws RecordNotFoundException {
		Optional<Employee> employee = employeeRepository.findById(id);

		if (employee.isPresent()) {
			employeeRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No employee record exist for the id " + id);
		}
		return "Success";
	}

}
