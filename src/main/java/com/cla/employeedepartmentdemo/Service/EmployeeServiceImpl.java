package com.cla.employeedepartmentdemo.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cla.employeedepartmentdemo.Repository.DepartmentRepository;
import com.cla.employeedepartmentdemo.Repository.EmployeeRepository;
import com.cla.employeedepartmentdemo.dto.DepartmentDTO;
import com.cla.employeedepartmentdemo.dto.EmployeeDTO;
import com.cla.employeedepartmentdemo.entity.Department;
import com.cla.employeedepartmentdemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Resource
	private EmployeeRepository employeeRepository;

	@Resource
	private DepartmentRepository departmentRepository;
	
	@Transactional
	@Override
	public List<EmployeeDTO> getAllEmployee() {
		List<EmployeeDTO> employeeDTO = new ArrayList<>();
		List<Employee> employee = employeeRepository.findAll();
		employee.stream().forEach(emp -> {
			EmployeeDTO empdto = mapEntityToDto(emp);
			employeeDTO.add(empdto);
		});
		return employeeDTO;
	}

	@Transactional
	@Override
	public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		mapDtoToEntity(employeeDTO, employee);
		Employee savedEmployee = employeeRepository.save(employee);
		return mapEntityToDto(savedEmployee);
	}
	
	@Cacheable(value="employeeCache", key="#empId")
	@Transactional
	@Override
	public EmployeeDTO updateEmployee(Integer empId, EmployeeDTO employeeDTO) {
		Employee employee = employeeRepository.getById(empId);
		employee.getDepartmentSet().clear();
		mapDtoToEntity(employeeDTO, employee);
		Employee emp = employeeRepository.save(employee);
		System.out.println("Fetching data from database");
		return mapEntityToDto(emp);
	}
	
	@Transactional
	@Override
	public String deleteEmployee(Integer empId) {
		Optional<Employee> employee = employeeRepository.findById(empId);
		if (employee.isPresent()) {
			employee.get().removeDepartments();
			employeeRepository.deleteById(empId);
			return "Deleted Successfully " + empId;
		}
		return null;
	}

	private EmployeeDTO mapEntityToDto(Employee employee) {
		EmployeeDTO empdto = new EmployeeDTO();
		empdto.setEmployeeName(employee.getEmployeeName());
		empdto.setEmployeeId(employee.getEmployeeId());
		empdto.setEmployeePhone(employee.getEmployeePhone());
		empdto.setEmployeeAddress(employee.getEmployeeAddress());
		empdto.setEmployeeDesignation(employee.getEmployeeDesignation());
		empdto.setDepartmentSet(employee.getDepartmentSet());
		return empdto;
	}

	private void mapDtoToEntity(EmployeeDTO employeeDTO, Employee employee) {
		employee.setEmployeeName(employeeDTO.getEmployeeName());
		employee.setEmployeeId(employeeDTO.getEmployeeId());
		employee.setEmployeePhone(employeeDTO.getEmployeePhone());
		employee.setEmployeeAddress(employeeDTO.getEmployeeAddress());
		employee.setEmployeeDesignation(employeeDTO.getEmployeeDesignation());
		
		  
		  if(null==employee.getDepartmentSet()) { 
			  employee.setDepartmentSet(new HashSet<>()); 
			  }
		  employeeDTO.getDepartmentSet().stream().forEach(departmentName ->
		  { 
			  Department department = departmentRepository.findByDepartmentId(departmentName.getDepartmentId()); 
			  if(null==department) 
			  { 
				  department=new Department();
				  department.setEmployeeSet(new HashSet<>()); 
			  }
			  department.setDepartmentId(departmentName.getDepartmentId());
			  department.setDepartmentName(departmentName.getDepartmentName());
			  department.setDepartmentLocation(departmentName.getDepartmentLocation());
			  employee.addDepartment(department); 
		  });
		 
	}

}
