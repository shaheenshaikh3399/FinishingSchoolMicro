package com.micro.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
