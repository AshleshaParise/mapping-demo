package com.cla.employeedepartmentdemo.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ManyToMany;

import com.cla.employeedepartmentdemo.entity.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class DepartmentDTO {
	
	private Integer departmentId;
	
	private String departmentName;
	
	private String departmentLocation;

	private Set<Employee> employeeSet=new HashSet<>();

}
