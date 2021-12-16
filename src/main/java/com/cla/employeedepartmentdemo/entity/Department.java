package com.cla.employeedepartmentdemo.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@Entity
@Table(name="DEPARTMENT")
public class Department implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DEPT_ID")
	private Integer departmentId;
	
	@Column(name="DEPT_NAME")
	private String departmentName;
	
	@Column(name="DEPT_LOCATION")
	private String departmentLocation;
	

	@ManyToMany(mappedBy="departmentSet",cascade = {CascadeType.ALL})
	@JsonIgnore
	private Set<Employee> employeeSet=new HashSet<>();


	public Department() {
		
	}


	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName
				+ ", departmentLocation=" + departmentLocation + ", employeeSet=" + employeeSet + "]";
	}
	
	
	
	public void addEmployee(Employee employee) {
		this.employeeSet.add(employee);
		employee.getDepartmentSet().add(this);
	}
	
	public void removeEmployee(Employee employee) {
		this.getEmployeeSet().remove(employee);
		employee.getDepartmentSet().remove(this);
	}
	
	public void removeEmployees() {
		for(Employee employee:new HashSet<>(employeeSet)) {
			removeEmployee(employee);
		}
	}


	public Department(Integer departmentId, String departmentName, String departmentLocation) {
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.departmentLocation = departmentLocation;
	}	

}
