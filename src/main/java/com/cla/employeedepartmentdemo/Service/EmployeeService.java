package com.cla.employeedepartmentdemo.Service;

import java.util.List;

import com.cla.employeedepartmentdemo.dto.EmployeeDTO;

public interface EmployeeService {
	
	public List<EmployeeDTO> getAllEmployee();
	public EmployeeDTO addEmployee(EmployeeDTO employeeDTO);
	public EmployeeDTO updateEmployee(Integer empId, EmployeeDTO employeeDTO);
	public String deleteEmployee(Integer empId);

}
