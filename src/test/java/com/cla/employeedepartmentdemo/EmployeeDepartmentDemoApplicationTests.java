package com.cla.employeedepartmentdemo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cla.employeedepartmentdemo.Repository.DepartmentRepository;
import com.cla.employeedepartmentdemo.Service.DepartmentService;
import com.cla.employeedepartmentdemo.Service.DepartmentServiceImpl;
import com.cla.employeedepartmentdemo.entity.Department;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {EmployeeDepartmentDemoApplicationTests.class})
class EmployeeDepartmentDemoApplicationTests {
	
	@InjectMocks
	private DepartmentServiceImpl departmentServiceImpl;
	
	@Mock
	private DepartmentRepository departmentRepository;

	@Test
	@Order(1)
	public void getDepartmenTest() 
	{
		List<Department> department=new ArrayList<>();
		department.add(new Department(1,"CSE","PUNE"));
		department.add(new Department(3,"Mech","PUNE"));
	    Mockito.when(departmentRepository.findAll()).thenReturn(department);
	    assertEquals(2,departmentServiceImpl.getAllDepartment().size());
		
	}
	
}
