package com.usk.emplservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.usk.emplservice.dto.DailyActivityDto;
import com.usk.emplservice.dto.EmployeeActivity;
import com.usk.emplservice.dto.EmployeeDto;
import com.usk.emplservice.entity.Employee;
import com.usk.emplservice.feign.ActivityClient;
import com.usk.emplservice.repository.EmplRepository;
import com.usk.emplservice.service.EmplService;


@Service
public class EmplServiceImpl implements EmplService {

	private static final Logger logger = LoggerFactory.getLogger(EmplServiceImpl.class);
	@Autowired
	EmplRepository emplRepository;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	ActivityClient activityClient;
	
	@Override
	public List<Employee> addEmployee(List<EmployeeDto> employeeDto) throws  Exception {
		List<EmployeeDto> employees = getAllEmployees();
		List<Integer> dbcodes = employees.stream().map(e -> e.getCode()).collect(Collectors.toList());
		List<String> emailIds = employees.stream().map(e -> e.getEmailId()).collect(Collectors.toList());
		List<Long> phoneNumbers = employees.stream().map(e -> e.getPhoneNumber()).collect(Collectors.toList());
		List<Employee> employeeList = new ArrayList<Employee>();
		try {
			employeeDto.forEach(emp -> {
			if(dbcodes.contains(emp.getCode())) {
				logger.error("employee with emp code already exists");
			} else if( emailIds.contains(emp.getEmailId())) {
				logger.error("employee with emailid already exists");
			} else  if(phoneNumbers.contains(emp.getPhoneNumber())) {
				logger.error("employee with  phone number already exists");
			}
			else {
				Employee employee = new Employee();
				employee.setCode(emp.getCode());
				employee.setName(emp.getName());
				employee.setJobTitle(emp.getJobTitle());
				employee.setEmailId(emp.getEmailId());
				employee.setExperience(emp.getExperience());
				employee.setPhoneNumber(emp.getPhoneNumber());
				employee.setLocation(emp.getLocation());
				employee.setProject_Status(emp.getProject_Status());
				employeeList.add(emplRepository.save(employee));
				logger.info("$$$ SENDING MESSAGE TO KAFKA TOPIC $$$");
				kafkaTemplate.send("emptopic", "Hello from Emp Service");
			}
		});
		} catch(Exception e) {
			logger.error("", e);
			throw new Exception("employee with emp code already exists");
		}
		return employeeList;
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> empList = (List<Employee>) emplRepository.findAll();
		List<EmployeeDto> empDtoList = new ArrayList<EmployeeDto>();
		empList.stream().forEach(emp -> {
			EmployeeDto empDto = convertToDto(emp);
			empDtoList.add(empDto);
		});
		return empDtoList;
	}

	@Override
	public List<EmployeeDto> getEmployeeByName(String name) {
		List<EmployeeDto> employeeDtoList = new ArrayList();
		List<Employee> emp = emplRepository.findByName(name);
		emp.stream().forEach(employee ->{
			employeeDtoList.add( convertToDto(employee));
		});
		
//		EmployeeDto empDto = new EmployeeDto();
//		empDto.setCode(emp.getCode());
//		empDto.setName(emp.getName());
//		empDto.setJobTitle(emp.getJobTitle());
//		empDto.setEmailId(emp.getEmailId());
//		empDto.setExperience(emp.getExperience());
//		empDto.setPhoneNumber(emp.getPhoneNumber());
//		empDto.setLocation(emp.getLocation());
//		empDto.setProject_Status(emp.getProject_Status());
		return employeeDtoList;
	}

	@Override
	public EmployeeDto updateEmployee(EmployeeDto emp) {
		// TODO Auto-generated method stub
		Employee employee = emplRepository.findByCode(emp.getCode());
		employee.setCode(emp.getCode());
		employee.setName(emp.getName());
		employee.setJobTitle(emp.getJobTitle());
		employee.setEmailId(emp.getEmailId());
		employee.setExperience(emp.getExperience());
		employee.setPhoneNumber(emp.getPhoneNumber());
		employee.setLocation(emp.getLocation());
		employee.setProject_Status(emp.getProject_Status());
		Employee empObj =  emplRepository.save(employee);
		EmployeeDto empDto = convertToDto(empObj);
		return empDto;
	}

	private EmployeeDto convertToDto(Employee emp) {
		// TODO Auto-generated method stub
		EmployeeDto empDto = new EmployeeDto();
		empDto.setCode(emp.getCode());
		empDto.setName(emp.getName());
		empDto.setJobTitle(emp.getJobTitle());
		empDto.setEmailId(emp.getEmailId());
		empDto.setExperience(emp.getExperience());
		empDto.setPhoneNumber(emp.getPhoneNumber());
		empDto.setLocation(emp.getLocation());
		empDto.setProject_Status(emp.getProject_Status());
		return empDto;
	}

	@Override
	@Transactional
	public void deleteByCode(Integer code) {
		// TODO Auto-generated method stub
		emplRepository.deleteByCode(code);
	}

	@Override
	public EmployeeActivity getEmployeeActivities(Integer code){
		Employee emp = emplRepository.findByCode(code);
		List<DailyActivityDto> dailyActivities = activityClient.getDailyActivitiesByReqParam(code);
		EmployeeActivity employeeActivities = new EmployeeActivity();
		employeeActivities.setEmployeeAcitivites(dailyActivities);
		employeeActivities.setEmployeeDetails(emp);
//		dailyActivities.stream().forEach( activity -> {
//			EmployeeActivity employeeActivity = new EmployeeActivity();
//			employeeActivity.setCode(emp.getCode());
//			employeeActivity.setName(emp.getName());
//		    employeeActivity.setJobTitle(emp.getJobTitle());
//		    employeeActivity.setEmailId(emp.getEmailId());
//		    employeeActivity.setExperience(emp.getExperience());
//		    employeeActivity.setPhoneNumber(emp.getPhoneNumber());
//		    employeeActivity.setLocation(emp.getLocation());
//		    employeeActivity.setProject_Status(emp.getProject_Status());
//			employeeActivity.setDate(activity.getDate());
//			employeeActivity.setDescription(activity.getDescription());
//			employeeActivity.setStatus(activity.getStatus());
//			employeeActivities.add(employeeActivity);
//		});
		
		return employeeActivities;
	 }
	}

	
