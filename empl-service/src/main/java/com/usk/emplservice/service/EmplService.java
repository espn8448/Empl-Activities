package com.usk.emplservice.service;

import java.util.List;

import com.usk.emplservice.dto.EmployeeActivity;
import com.usk.emplservice.dto.EmployeeDto;
import com.usk.emplservice.entity.Employee;

public interface EmplService {
	
   List<Employee> addEmployee(List<EmployeeDto> employeeDto)  throws Exception;
   List<EmployeeDto> getAllEmployees();
   List<EmployeeDto> getEmployeeByName(String name);
   EmployeeDto updateEmployee(EmployeeDto employeeDto);
   void deleteByCode(Integer code);
   EmployeeActivity getEmployeeActivities(Integer code);
   
}
