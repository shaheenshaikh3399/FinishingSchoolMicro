package com.micro.employee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.employee.dto.ApiResponseDto;
import com.micro.employee.dto.EmployeeDto;
import com.micro.employee.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {
	
	private EmployeeService employeeService;
	@PostMapping
	public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
		EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
		return new ResponseEntity<EmployeeDto>(savedEmployee, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ApiResponseDto> getEmployeeById(@PathVariable("id") Long employeeID){
		ApiResponseDto apiResponseDto= employeeService.getEmployee(employeeID);
		return new ResponseEntity<ApiResponseDto>(apiResponseDto, HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<EmployeeDto>  updateEmployee (@RequestBody EmployeeDto employeeDto, @PathVariable("id") Long employeeId){
		EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(employeeDto, employeeId);
		return new ResponseEntity<EmployeeDto>(updatedEmployeeDto, HttpStatus.ACCEPTED);
		
	}

}
