package com.cla.employeedepartmentdemo.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cla.employeedepartmentdemo.Repository.DepartmentRepository;
import com.cla.employeedepartmentdemo.Repository.EmployeeRepository;
import com.cla.employeedepartmentdemo.dto.DepartmentDTO;
import com.cla.employeedepartmentdemo.dto.EmployeeDTO;
import com.cla.employeedepartmentdemo.entity.Department;
import com.cla.employeedepartmentdemo.entity.Employee;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Resource
	private EmployeeRepository employeeRepository;

	@Resource
	private DepartmentRepository departmentRepository;
	
	@Transactional
	@Override
	public List<DepartmentDTO> getAllDepartment() {
		List<DepartmentDTO> departmentDTO = new ArrayList<>();
		List<Department> department = departmentRepository.findAll();
		department.stream().forEach(dept -> {
			DepartmentDTO deptdto = mapEntityToDto(dept);
			departmentDTO.add(deptdto);
		});
		return departmentDTO;
	}
	
	
	@Transactional
	@Override
	public DepartmentDTO addDepartment(DepartmentDTO departmentDTO) 
	{
		Department depertment = new Department();
		mapDtoToEntity(departmentDTO, depertment);
		Department savedDepartment = departmentRepository.save(depertment);
		return mapEntityToDto(savedDepartment);
	}
	
	@Transactional
	@Override
	public DepartmentDTO updateDepartment(Integer departmentId, DepartmentDTO departmentDTO) {
		Department department = departmentRepository.getById(departmentId);
		department.getEmployeeSet().clear();
		mapDtoToEntity(departmentDTO, department);
		Department dept = departmentRepository.save(department);
		return mapEntityToDto(dept);
	}
	
	@Transactional
	@Override
	public String deleteDepartment(Integer departmentId) {
		Optional<Department> department = departmentRepository.findById(departmentId);
		if (department.isPresent()) {
			department.get().removeEmployees();
			departmentRepository.deleteById(department.get().getDepartmentId());
			return "Deleted Successfully " + departmentId;
		}
		return null;
	}
	
	private DepartmentDTO mapEntityToDto(Department department)
	{
		DepartmentDTO deptdto = new DepartmentDTO();
		deptdto.setDepartmentId(department.getDepartmentId());
		deptdto.setDepartmentName(department.getDepartmentName());
		deptdto.setDepartmentLocation(department.getDepartmentLocation());
		deptdto.setEmployeeSet(department.getEmployeeSet());
		return deptdto;
	}

	private void mapDtoToEntity(DepartmentDTO depertmentDTO, Department department) {
		department.setDepartmentId(depertmentDTO.getDepartmentId());
		department.setDepartmentName(depertmentDTO.getDepartmentName());
		department.setDepartmentLocation(depertmentDTO.getDepartmentLocation());
		
		  
		  if(null==department.getEmployeeSet()) 
		  { 
			  department.setEmployeeSet(new HashSet<>()); 
		  }
		  depertmentDTO.getEmployeeSet().stream().forEach(employees ->
		  { 
			  Employee employee = employeeRepository.findByEmployeeId(employees.getEmployeeId()); 
			  if(null==employee) 
			  { 
				  employee=new Employee();
				  employee.setDepartmentSet(new HashSet<>()); 
			  }
			  
			  employee.setEmployeeName(employees.getEmployeeName());
			  employee.setEmployeeId(employees.getEmployeeId());
			  employee.setEmployeePhone(employees.getEmployeePhone());
			  employee.setEmployeeAddress(employees.getEmployeeAddress());
			  employee.setEmployeeDesignation(employees.getEmployeeDesignation());
			 
			  department.addEmployee(employee); 
		  });
		 
	}

}
