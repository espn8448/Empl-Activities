package com.usk.emplservice.dto;

import java.util.List;

import com.usk.emplservice.entity.Employee;

public class EmployeeActivity {
	
	private Employee employeeDetails;
	
	private List<DailyActivityDto> employeeAcitivites;
	
	public Employee getEmployeeDetails() {
		return employeeDetails;
	}
	public void setEmployeeDetails(Employee employeeDetails) {
		this.employeeDetails = employeeDetails;
	}
	public List<DailyActivityDto> getEmployeeAcitivites() {
		return employeeAcitivites;
	}
	public void setEmployeeAcitivites(List<DailyActivityDto> employeeAcitivites) {
		this.employeeAcitivites = employeeAcitivites;
	}

//	 private Integer code;
//		
//		private String name;
//		
//		private String jobTitle;
//		
//		private String emailId;
//		
//		private Integer experience;
//		
//		private Long phoneNumber;
//		
//		private String location;
//		
//		private String project_Status; 
//		
//		private LocalDate date;
//		
//		private String description; 
//		
//		private String status;
//
//		public Integer getCode() {
//			return code;
//		}
//
//		public void setCode(Integer code) {
//			this.code = code;
//		}
//
//		public String getName() {
//			return name;
//		}
//
//		public void setName(String name) {
//			this.name = name;
//		}
//
//		public LocalDate getDate() {
//			return date;
//		}
//
//		public void setDate(LocalDate date) {
//			this.date = date;
//		}
//
//		public String getDescription() {
//			return description;
//		}
//
//		public void setDescription(String description) {
//			this.description = description;
//		}
//
//		public String getStatus() {
//			return status;
//		}
//
//		public void setStatus(String status) {
//			this.status = status;
//		}
//
//		public String getJobTitle() {
//			return jobTitle;
//		}
//
//		public void setJobTitle(String jobTitle) {
//			this.jobTitle = jobTitle;
//		}
//
//		public String getEmailId() {
//			return emailId;
//		}
//
//		public void setEmailId(String emailId) {
//			this.emailId = emailId;
//		}
//
//		public Integer getExperience() {
//			return experience;
//		}
//
//		public void setExperience(Integer experience) {
//			this.experience = experience;
//		}
//
//		public Long getPhoneNumber() {
//			return phoneNumber;
//		}
//
//		public void setPhoneNumber(Long phoneNumber) {
//			this.phoneNumber = phoneNumber;
//		}
//
//		public String getLocation() {
//			return location;
//		}
//
//		public void setLocation(String location) {
//			this.location = location;
//		}
//
//		public String getProject_Status() {
//			return project_Status;
//		}
//
//		public void setProject_Status(String project_Status) {
//			this.project_Status = project_Status;
//		}
//	
}
