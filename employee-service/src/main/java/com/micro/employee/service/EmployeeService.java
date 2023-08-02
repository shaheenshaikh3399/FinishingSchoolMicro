package com.micro.employee.service;

import org.springframework.stereotype.Service;

import com.micro.employee.dto.ApiResponseDto;
import com.micro.employee.dto.EmployeeDto;
@Service
public interface EmployeeService {
	public EmployeeDto saveEmployee (EmployeeDto employeeDto);
	
	public ApiResponseDto getEmployee (Long employeeId);
	
	public EmployeeDto updateEmployee (EmployeeDto employeeDto, Long employeeId);


}
