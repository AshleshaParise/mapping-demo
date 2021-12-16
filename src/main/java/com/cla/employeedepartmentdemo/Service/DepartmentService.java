package com.cla.employeedepartmentdemo.Service;

import java.util.List;

import com.cla.employeedepartmentdemo.dto.DepartmentDTO;


public interface DepartmentService {
	public List<DepartmentDTO> getAllDepartment();
	public DepartmentDTO addDepartment(DepartmentDTO departmentDTO);
	public DepartmentDTO updateDepartment(Integer departmentId, DepartmentDTO departmentDTO);
	public String deleteDepartment(Integer departmentId);

}
