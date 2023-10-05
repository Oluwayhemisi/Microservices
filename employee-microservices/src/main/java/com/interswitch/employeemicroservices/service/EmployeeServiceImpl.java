package com.interswitch.employeemicroservices.service;

import com.interswitch.employeemicroservices.client.ApiClient;
import com.interswitch.employeemicroservices.dto.DepartmentDto;
import com.interswitch.employeemicroservices.dto.EmployeeApiResponse;
import com.interswitch.employeemicroservices.dto.EmployeeDto;
import com.interswitch.employeemicroservices.exceptions.EmployeeException;
import com.interswitch.employeemicroservices.model.Employee;
import com.interswitch.employeemicroservices.repository.EmployeeRepository;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{


    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    private final ApiClient apiClient;
    private  final WebClient webClient;


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        validateEmployee(employeeDto);
        Employee employee = new Employee();
        employee.setEmail(employeeDto.getEmail());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setDepartmentCode(employeeDto.getDepartmentCode());
        employeeRepository.save(employee);

        return modelMapper.map(employee,EmployeeDto.class);
    }
    private void validateEmployee(EmployeeDto employeeDto){

        if(employeeRepository.findByEmail(employeeDto.getEmail()).isPresent()){
            throw new EmployeeException("Employee Id already exist",404);
        }
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new EmployeeException("Employee Id Already Exist",404));
        employee.setEmail(employeeDto.getEmail());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employeeRepository.save(employee);
        return modelMapper.map(employee,EmployeeDto.class);
    }


//    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public EmployeeApiResponse getEmployeeById(Long id) {

        LOGGER.info("inside getEmployeeById() method");
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new EmployeeException("Employee Id does not Exist",404));
//        DepartmentDto departmentDto = apiClient.getDepartmentByDepartmentCode(employee.getDepartmentCode());

        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8484/api/v1/department/code/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

//        EmployeeDto employeeDto = new EmployeeDto();
        EmployeeDto employeeDto1 = modelMapper.map(employee,EmployeeDto.class);

        EmployeeApiResponse employeeApiResponse = new EmployeeApiResponse();
        employeeApiResponse.setEmployeeDto(employeeDto1);
        employeeApiResponse.setDepartmentDto(departmentDto);
        return employeeApiResponse;

    }

    public EmployeeApiResponse getDefaultDepartment(Long id, Exception exception){

        LOGGER.info("inside getDefaultDepartment() method");
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new EmployeeException("Employee Id does not Exist",404));

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentName("R & D");
        departmentDto.setDepartmentDescription("Research and Development");

        EmployeeDto employeeDto1 = modelMapper.map(employee,EmployeeDto.class);

        EmployeeApiResponse employeeApiResponse = new EmployeeApiResponse();
        employeeApiResponse.setEmployeeDto(employeeDto1);
        employeeApiResponse.setDepartmentDto(departmentDto);
        return employeeApiResponse;
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }
}
