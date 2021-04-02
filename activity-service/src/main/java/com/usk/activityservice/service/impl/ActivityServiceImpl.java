package com.usk.activityservice.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usk.activityservice.dto.DailyActivityDto;
import com.usk.activityservice.entity.DailyActivity;
import com.usk.activityservice.repository.ActivityRepository;
import com.usk.activityservice.service.ActivityService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {

	private static final Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);

	@Autowired
	ActivityRepository activityRepository;

	@Override
	public List<DailyActivity> addDailyActivity(List<DailyActivityDto> dailyActivityDto) {
		List<DailyActivity> dailyActivityList = new ArrayList<DailyActivity>();
		dailyActivityDto.stream().forEach(activityDto -> {
			DailyActivity dailyActivity = new DailyActivity();
			dailyActivity.setCode(activityDto.getCode());
			dailyActivity.setDescription(activityDto.getDescription());
			dailyActivity.setDate(activityDto.getDate());
			dailyActivity.setEmpName(activityDto.getEmp_name());
			dailyActivity.setStatus(activityDto.getStatus());
			dailyActivityList.add(activityRepository.save(dailyActivity));
		});

		return dailyActivityList;
	}

	@Override
	public List<DailyActivityDto> getDailyActivityByName(String empName) {
		List<DailyActivityDto> activityDtos = new ArrayList<DailyActivityDto>();
		List<DailyActivity> activities = activityRepository.findByEmpName(empName);
		activities.stream().forEach(activity -> {
			DailyActivityDto activityDto = new DailyActivityDto();
			activityDto.setCode(activity.getCode());
			activityDto.setDate(activity.getDate());
			activityDto.setDescription(activity.getDescription());
			activityDto.setEmp_name(activity.getEmpName());
			activityDto.setStatus(activity.getStatus());
			activityDtos.add(activityDto);
		});

		return activityDtos;
	}

	@Override
	public List<DailyActivityDto> getDailyActivitiesByCode(Integer code) {
		List<DailyActivityDto> dailyActivityDtoList = new ArrayList<DailyActivityDto>();
		List<DailyActivity> dailyActivities = activityRepository.findByCode(code);

		dailyActivities.stream().forEach(activityObj -> {
			DailyActivityDto activityObjDto = new DailyActivityDto();
			activityObjDto.setCode(activityObj.getCode());
			activityObjDto.setDate(activityObj.getDate());
			activityObjDto.setDescription(activityObj.getDescription());
			activityObjDto.setEmp_name(activityObj.getEmpName());
			activityObjDto.setStatus(activityObj.getStatus());
			dailyActivityDtoList.add(activityObjDto);
		});
		return dailyActivityDtoList;
	}

	@Override
	public List<DailyActivity> getAllDailyActivities(LocalDate date) {
		List<DailyActivity> activitiesByDate = activityRepository.findByDate(date);
		logger.info("list size by date {}", activitiesByDate.size());
		return activitiesByDate;
	}

	@Override
	public DailyActivity updateDailyActivities(Integer id, DailyActivity dailyActivity) {
		DailyActivity updatedActivity = null;
		DailyActivity dailyActivityObj = getActivityById(id);
		if (dailyActivityObj != null) {
			dailyActivityObj.setCode(dailyActivity.getCode());
			dailyActivityObj.setDate(dailyActivity.getDate());
			dailyActivityObj.setDescription(dailyActivity.getDescription());
			dailyActivityObj.setEmpName(dailyActivity.getEmpName());
			dailyActivityObj.setStatus(dailyActivity.getStatus());
			updatedActivity = activityRepository.save(dailyActivityObj);
		}
		return updatedActivity;
	}

	public DailyActivity getActivityById(Integer id) {
		Optional<DailyActivity> activityById = activityRepository.findById(id);
		if (activityById.isPresent()) {
			return activityById.get();
		} else
			return null;
	}

}
