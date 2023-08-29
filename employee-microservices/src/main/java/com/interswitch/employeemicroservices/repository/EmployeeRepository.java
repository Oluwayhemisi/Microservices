package com.interswitch.employeemicroservices.repository;

import com.interswitch.employeemicroservices.dto.EmployeeDto;
import com.interswitch.employeemicroservices.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
}
