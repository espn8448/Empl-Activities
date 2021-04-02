package com.usk.emplservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usk.emplservice.dto.DailyActivityDto;
import com.usk.emplservice.dto.EmployeeActivity;
import com.usk.emplservice.dto.EmployeeDto;
import com.usk.emplservice.entity.Employee;
import com.usk.emplservice.feign.ActivityClient;
import com.usk.emplservice.repository.EmplRepository;
import com.usk.emplservice.service.EmplService;


@RestController
@RequestMapping("/api/employee")
public class EmplController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmplController.class);
	@Autowired
	EmplService emplService;
	
	@Autowired
	EmplRepository emplRepository;
	
	@Autowired
	ActivityClient activityClient;
	
	@GetMapping("/port")
	public String getInfo() {
		return activityClient.getInfo();
	}
	
	@PostMapping("/add")
	public ResponseEntity<Object> addEmployee(@RequestBody List<EmployeeDto> employeeDto) {
		List<Employee> list = new ArrayList<Employee>();
		ResponseEntity<Object> responseEntity = null;
		try {
			list = emplService.addEmployee(employeeDto);
			if (!list.isEmpty()) {
				responseEntity = new ResponseEntity<>("Employee Details Added successfully", HttpStatus.OK);																							
			} else {
				return new ResponseEntity<>("Error while saving employee object", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			logger.error("Exception while saving employee object", e);
		}
		return responseEntity;
	}
		
	@GetMapping
	public List<EmployeeDto> getEmployees(){
		return emplService.getAllEmployees();
	}
	
	@GetMapping("/{name}")
	public List<EmployeeDto> getEmployeeByName(@PathVariable String name){
		return emplService.getEmployeeByName(name);
	}
	
	@PatchMapping("/edit")
	public String editEmployee(@RequestBody EmployeeDto employeeDto){
		EmployeeDto empDto = emplService.updateEmployee(employeeDto);
		if(empDto!=null) {return "Employee Details Updated successfully";
		} else {
			return "Error in Updating the Employee Details";
		}
		
	}
	
	@GetMapping("/activityClient")
	public EmployeeActivity getEmployeeActivities(@RequestParam Integer code){
		Employee emp = emplRepository.findByCode(code);
		List<DailyActivityDto> dailyActivities = activityClient.getDailyActivitiesByReqParam(code);
		EmployeeActivity employeeActivities = new EmployeeActivity();
		employeeActivities.setEmployeeAcitivites(dailyActivities);
		employeeActivities.setEmployeeDetails(emp);
	    	return employeeActivities;
	}
	
	@DeleteMapping("/delete/{code}")
	public void deleteEmployee(@PathVariable Integer code) {
		emplService.deleteByCode(code);
	}
	}
	
