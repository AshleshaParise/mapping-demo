package com.cla.employeedepartmentdemo.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="EMPLOYEE")
public class Employee implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="EMP_ID")
	private Integer employeeId;
	
	@Column(name="EMP_NAME")
	private String employeeName;
	
	@Column(name="EMP_PHONE_NO")
	private Integer employeePhone;
	
	@Column(name="EMP_ADDRESS")
	private String employeeAddress;
	
	@Column(name="EMP_DESIGNATION")
	private String employeeDesignation;
	
	@ManyToMany(cascade= {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name="EMPLOYEE_DEPARTMENT", joinColumns = { @JoinColumn(name = "EMP_ID") },   
    inverseJoinColumns = { @JoinColumn(name = "DEPT_ID") })
	private Set<Department> departmentSet;

	
	public Employee() {

	}


	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeePhone="
				+ employeePhone + ", employeeAddress=" + employeeAddress + ", employeeDesignation="
				+ employeeDesignation + ", departmentSet=" + departmentSet + "]";
	}
	
	public void addDepartment(Department department) {
		this.departmentSet.add(department);
		department.getEmployeeSet().add(this);
	}
	
	public void removeDepartment(Department department) {
		this.getDepartmentSet().remove(department);
		department.getEmployeeSet().remove(this);
	}
	
	public void removeDepartments() {
		for(Department department:new HashSet<>(departmentSet)) {
			removeDepartment(department);
		}
	}
}
