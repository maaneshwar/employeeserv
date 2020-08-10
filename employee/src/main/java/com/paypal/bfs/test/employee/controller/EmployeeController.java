package com.paypal.bfs.test.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.bfs.test.employee.entity.Employee;
import com.paypal.bfs.test.employee.exception.RecordNotFoundException;
import com.paypal.bfs.test.employee.service.EmployeeService;

@RestController
@RequestMapping(path = "/v1/bfs/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> list = employeeService.getAllEmployees();
		return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer id) throws RecordNotFoundException {
		Employee entity = employeeService.getEmployeeById(id);
		return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Employee> createEmployee(Employee employee) throws RecordNotFoundException {
		Employee updated = employeeService.createEmployee((employee));
		return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public String deleteEmployeeById(@PathVariable("id") Integer id) throws RecordNotFoundException {
		return employeeService.deleteEmployeeById(id);
	}
}
