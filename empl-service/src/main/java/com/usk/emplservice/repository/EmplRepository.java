package com.usk.emplservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.usk.emplservice.entity.Employee;


@Repository
public interface EmplRepository extends CrudRepository<Employee, Integer>{

	 Employee findByCode(Integer code);

	List<Employee> findByName(String name);

	void deleteByCode(Integer code);
	
	
}
