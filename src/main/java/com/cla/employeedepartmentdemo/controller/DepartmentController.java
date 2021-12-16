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

import com.cla.employeedepartmentdemo.Service.DepartmentService;
import com.cla.employeedepartmentdemo.Service.EmployeeService;
import com.cla.employeedepartmentdemo.dto.DepartmentDTO;
import com.cla.employeedepartmentdemo.dto.EmployeeDTO;

@RestController
@RequestMapping(value ="/api")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/department")
	public ResponseEntity<List<DepartmentDTO>> getAllDepartments(){
		List<DepartmentDTO> department=departmentService.getAllDepartment();
		return new ResponseEntity<>(department,HttpStatus.OK);
		
	} 
	
	@PostMapping("/department")
	public ResponseEntity<DepartmentDTO> addDepartments(@RequestBody DepartmentDTO departmentDTO ){
		DepartmentDTO deptDto=departmentService.addDepartment(departmentDTO);
		return new ResponseEntity<>(deptDto,HttpStatus.CREATED);
		
	} 
	
	@PutMapping("/department/{departmentId}")
	public ResponseEntity<DepartmentDTO> updateDepartments(@PathVariable(name="departmentId") Integer departmentId,
			@RequestBody DepartmentDTO departmentDTO )
	{
		DepartmentDTO deptDto=departmentService.updateDepartment(departmentId,departmentDTO);
		return new ResponseEntity<>(deptDto,HttpStatus.CREATED);
	} 
	
	@DeleteMapping("/department/{departmentId}")
	public ResponseEntity<String> deleteDepartments(@PathVariable(name="departmentId") Integer departmentId)
	{
		String message=departmentService.deleteDepartment(departmentId);
		return new ResponseEntity<>(message,HttpStatus.OK);
	} 

}
