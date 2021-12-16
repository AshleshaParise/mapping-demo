package com.cla.employeedepartmentdemo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cla.employeedepartmentdemo.entity.Department;
import com.cla.employeedepartmentdemo.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	public Employee findByEmployeeId(Integer employeeId);

}
