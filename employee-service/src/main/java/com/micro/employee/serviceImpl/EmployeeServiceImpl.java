package com.micro.employee.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.micro.employee.dto.ApiResponseDto;
import com.micro.employee.dto.DepartmentDto;
import com.micro.employee.dto.EmployeeDto;
import com.micro.employee.dto.OrganizationDto;
import com.micro.employee.entity.Employee;
import com.micro.employee.repository.EmployeeRepository;
import com.micro.employee.service.EmployeeService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private  static final Logger LOGGER=  LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
private EmployeeRepository employeeRepository;
//private RestTemplate restTemplate;
//private ApiClient apiClient;
private WebClient webClient;
	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		
		// TODO Auto-generated method stub
		Employee employee = new Employee(
				employeeDto.getId(),
				employeeDto.getFirstName(),
				employeeDto.getLastName(),
				employeeDto.getEmail(),
				employeeDto.getDepartmentCode(),
				employeeDto.getOrganizationCode());
		Employee savedEmployee= employeeRepository.save(employee);
		
		EmployeeDto savedEmployeeDto= new EmployeeDto(savedEmployee.getId(),
				savedEmployee.getFirstName(),
				savedEmployee.getLastName(),
				savedEmployee.getEmail(),
				savedEmployee.getDepartmentCode(),
				savedEmployee.getOrganizationCode());
		return savedEmployeeDto;
	}
	@Override
	@CircuitBreaker(name="${spring.application.name}", fallbackMethod = "getDefaultDepartment")
	//@Retry(name="${spring.application.name}", fallbackMethod = "getDefaultDepartment")
	public ApiResponseDto getEmployee(Long employeeId) {
		
		LOGGER.info("Inside getEmployee Method");
		// TODO Auto-generated method stub
		Employee employee= employeeRepository.findById(employeeId).get();
		
		//ResponseEntity<DepartmentDto> responseEntity=  restTemplate.getForEntity("http://localhost:8081/api/department/" + employee.getDepartmentCode(), DepartmentDto.class);
		
		//DepartmentDto departmentDto= responseEntity.getBody();
		
		//DepartmentDto departmentDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());
		
		
		 DepartmentDto departmentDto =
		  webClient.get().uri("http://localhost:8089/api/department/"+employee.
		  getDepartmentCode()) .retrieve() .bodyToMono(DepartmentDto.class) .block();
		 
		 OrganizationDto organizationDto =
				  webClient.get().uri("http://localhost:8083/api/organizations/"+employee.
				  getOrganizationCode())
				  .retrieve()
				  .bodyToMono(OrganizationDto.class)
				  .block();
		 
		 
		EmployeeDto savedEmployeeDto= new EmployeeDto(employee.getId(),
				employee.getFirstName(),
				employee.getLastName(),
				employee.getEmail(),
				employee.getDepartmentCode(),
				employee.getOrganizationCode());
		
		ApiResponseDto apiResponseDto = new ApiResponseDto();
		apiResponseDto.setEmployee(savedEmployeeDto);
		apiResponseDto.setDepartment(departmentDto);
		apiResponseDto.setOrganizationDto(organizationDto);
		
		return apiResponseDto;
	}
	@Override
	public EmployeeDto updateEmployee( EmployeeDto employeeDto ,Long employeeId) {
		// TODO Auto-generated method stub
		Employee existingEmployee = employeeRepository.findById(employeeId).get();
		 
		        existingEmployee.setFirstName(employeeDto.getFirstName());
				existingEmployee.setFirstName(existingEmployee.getFirstName());
				existingEmployee.setEmail(employeeDto.getEmail());
	
		
		EmployeeDto newEmployeedto= new EmployeeDto(existingEmployee.getId(),
				existingEmployee.getFirstName(),
				existingEmployee.getLastName(),
				existingEmployee.getEmail(),
				existingEmployee.getDepartmentCode(),
				existingEmployee.getOrganizationCode());
		
		
		
		
		return newEmployeedto;
	}
	
	public ApiResponseDto getDefaultDepartment(Long employeeId, Exception exception) {
		LOGGER.info("Inside getDefaultDepartment Method");
		
Employee employee= employeeRepository.findById(employeeId).get();
		
		DepartmentDto departmentDto= new DepartmentDto();
		departmentDto.setDepartmentName("T&D Deparment");
		departmentDto.setDepartmentDescription("Test and Diagonostic");
		departmentDto.setDepartmentCode("TD01");
		
		 
		EmployeeDto savedEmployeeDto= new EmployeeDto(employee.getId(),
				employee.getFirstName(),
				employee.getLastName(),
				employee.getEmail(),
				employee.getDepartmentCode(),
				employee.getOrganizationCode());
		
		ApiResponseDto apiResponseDto = new ApiResponseDto();
		apiResponseDto.setEmployee(savedEmployeeDto);
		apiResponseDto.setDepartment(departmentDto);
		
		return apiResponseDto;

	}

}
