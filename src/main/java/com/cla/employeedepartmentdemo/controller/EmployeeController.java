package com.cla.employeedepartmentdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cla.employeedepartmentdemo.Service.EmployeeService;
import com.cla.employeedepartmentdemo.dto.EmployeeDTO;

@RestController
@RequestMapping(value ="/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employee")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
		List<EmployeeDTO> employee=employeeService.getAllEmployee();
		return new ResponseEntity<>(employee,HttpStatus.OK);
		
	} 
	
	@PostMapping("/employee")
	public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO ){
		EmployeeDTO empDto=employeeService.addEmployee(employeeDTO);
		return new ResponseEntity<>(empDto,HttpStatus.CREATED);
		
	} 
	
	@PutMapping("/employee/{employeeId}")
	public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable(name="employeeId") Integer employeeId,
			@RequestBody EmployeeDTO employeedto )
	{
		EmployeeDTO empDto=employeeService.updateEmployee(employeeId,employeedto);
		return new ResponseEntity<>(empDto,HttpStatus.CREATED);
	} 
	
	@DeleteMapping("/employee/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable(name="employeeId") Integer employeeId)
	{
		String message=employeeService.deleteEmployee(employeeId);
		return new ResponseEntity<>(message,HttpStatus.OK);
	} 
		
}
