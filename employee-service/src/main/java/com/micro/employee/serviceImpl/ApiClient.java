package com.micro.employee.serviceImpl;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.micro.employee.dto.DepartmentDto;


//@FeignClient(name="DEPARTMENT-SERVICES")
public interface ApiClient {
	//@GetMapping("api/department/{department-code}")
	//DepartmentDto getDepartmentByCode(@PathVariable("department-code") String departmentCode);


}
