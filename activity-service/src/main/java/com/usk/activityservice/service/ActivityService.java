package com.usk.activityservice.service;

import java.time.LocalDate;
import java.util.List;

import com.usk.activityservice.dto.DailyActivityDto;
import com.usk.activityservice.entity.DailyActivity;


public interface ActivityService {
	
	List<DailyActivity> addDailyActivity(List<DailyActivityDto> dailyActivityDto);
	
	List<DailyActivity> getAllDailyActivities(LocalDate date);
	
	List<DailyActivityDto> getDailyActivityByName(String empName);
	
	DailyActivity updateDailyActivities(Integer id, DailyActivity dailyActivity);
	
	List<DailyActivityDto> getDailyActivitiesByCode(Integer code);
	
	//List<DailyActivityDto> getDailyActivityByDate();
	
	
}
