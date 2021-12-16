package com.cla.employeedepartmentdemo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

import com.cla.employeedepartmentdemo.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	//public Department findBydepartmentName(String departmentName);
	public Department findByDepartmentId(Integer departmentId);
	
}
