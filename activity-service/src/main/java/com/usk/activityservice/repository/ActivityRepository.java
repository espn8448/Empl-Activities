package com.usk.activityservice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.usk.activityservice.entity.DailyActivity;


@Repository
public interface ActivityRepository  extends CrudRepository<DailyActivity, Integer> {

	List<DailyActivity> findByDate(LocalDate date);
	
	List<DailyActivity> findByEmpName(String empName);
	
	List<DailyActivity> findByCode(Integer code);

}
