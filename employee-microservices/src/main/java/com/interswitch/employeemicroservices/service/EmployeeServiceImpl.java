package com.interswitch.employeemicroservices.service;

import com.interswitch.employeemicroservices.client.ApiClient;
import com.interswitch.employeemicroservices.dto.DepartmentDto;
import com.interswitch.employeemicroservices.dto.EmployeeApiResponse;
import com.interswitch.employeemicroservices.dto.EmployeeDto;
import com.interswitch.employeemicroservices.exceptions.EmployeeException;
import com.interswitch.employeemicroservices.model.Employee;
import com.interswitch.employeemicroservices.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{


    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    private final ApiClient apiClient;


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

    @Override
    public EmployeeApiResponse getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new EmployeeException("Employee Id does not Exist",404));
        DepartmentDto departmentDto = apiClient.getDepartmentByDepartmentCode(employee.getDepartmentCode());

        EmployeeDto employeeDto = new EmployeeDto();
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
