package com.usk.emplservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.usk.emplservice.dto.DailyActivityDto;

//@FeignClient(value = "activity-service", url = "http://localhost:8092/activity")
@FeignClient(name = "http://activity-service/activity")
public interface ActivityClient {
	
	@GetMapping("/api/dailyactivity/byCode")
    public List<DailyActivityDto> getDailyActivitiesByReqParam(@RequestParam Integer code);
	
	@GetMapping("/api/dailyactivity/port")
	public String getInfo();

}
