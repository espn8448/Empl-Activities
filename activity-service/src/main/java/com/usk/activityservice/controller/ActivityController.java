package com.usk.activityservice.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usk.activityservice.dto.DailyActivityDto;
import com.usk.activityservice.entity.DailyActivity;
import com.usk.activityservice.service.ActivityService;


@RestController
@RequestMapping("/api/dailyactivity")
public class ActivityController {

	@Autowired
	ActivityService activityService;
	
	@Autowired
	Environment environment;
	
	@GetMapping("/port")
	public String getInfo() {
		String port = environment.getProperty("local.server.port");
		return "From server "+port;
	}
	
	@PostMapping("/add")
	public String addDailyActivites(@RequestBody List<DailyActivityDto> dailyAcitivityDto){
		
		List<DailyActivity> list = activityService.addDailyActivity(dailyAcitivityDto);
		if(!list.isEmpty()) {
		 return "Daily Activities saved sucessful";
		} else {
			return "error in saving the activity list";
		}
		}
	
	@GetMapping("/{empName}")
	public List<DailyActivityDto> getDailyActivityByName(@PathVariable String empName){
		return activityService.getDailyActivityByName(empName);
	}
	
//	@GetMapping("/code/{code}")
	@GetMapping("/byCode")
	public List<DailyActivityDto> getDailyActivitiesByCode(@RequestParam Integer code){
		return activityService.getDailyActivitiesByCode(code);
	}
	
	@GetMapping("/getByDate")
	public List<DailyActivity> fetchActivitiesByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date){
		List<DailyActivity> list = activityService.getAllDailyActivities(date);
		return list;
	}
	
	@PutMapping("/{id}")
	public DailyActivity updateActivity(@PathVariable Integer id, @RequestBody DailyActivity dailyActivity) {
		DailyActivity dailyActivityObj = activityService.updateDailyActivities(id, dailyActivity);
		return dailyActivityObj;
	}
}
