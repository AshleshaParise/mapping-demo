package com.cla.employeedepartmentdemo.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;

import com.cla.employeedepartmentdemo.entity.Department;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class EmployeeDTO implements Serializable {
	
	private Integer employeeId;
	
	private String employeeName;
	
	private Integer employeePhone;
	
	private String employeeAddress;
	
	private String employeeDesignation;
	
	private Set<Department> departmentSet=new HashSet<>();



}
