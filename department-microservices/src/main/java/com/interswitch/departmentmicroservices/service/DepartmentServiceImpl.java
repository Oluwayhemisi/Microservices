package com.interswitch.departmentmicroservices.service;

import com.interswitch.departmentmicroservices.dto.DepartmentDto;
import com.interswitch.departmentmicroservices.exceptions.DepartmentException;
import com.interswitch.departmentmicroservices.model.Department;
import com.interswitch.departmentmicroservices.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
       validateDepartment(departmentDto);
       Department department = new Department();
       department.setDepartmentCode(generateDeptCode());
       department.setDepartmentName(departmentDto.getDepartmentName());
       department.setDepartmentDescription(departmentDto.getDepartmentDescription());
       departmentRepository.save(department);
      return modelMapper.map(department,DepartmentDto.class);
//       return BeanUtils.copyProperties(department,DepartmentDto.class);



    }
    private void validateDepartment(DepartmentDto departmentDto){

        if(departmentRepository.findByDepartmentCode(departmentDto.getDepartmentCode()).isPresent()){
            throw new DepartmentException("Department already exist",404);
        }
    }

    private String generateDeptCode(){
        int number = departmentRepository.findAll().size();
        String code = String.format("%02d",number+1);
        return "DEPT"+code;


    }

    @Override
    public DepartmentDto updateDepartment(Long id,DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(id).orElseThrow(()-> new DepartmentException("Department does not exist",404));
        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDepartmentDescription(departmentDto.getDepartmentDescription());
        departmentRepository.save(department);
        return modelMapper.map(department,DepartmentDto.class);
    }

    @Override
    public DepartmentDto findDeptByDepartmentCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode).orElseThrow(()-> new DepartmentException("Department does not exist",404));

        DepartmentDto departmentDto = new DepartmentDto();
//        return modelMapper.map(department, departmentDto);

        BeanUtils.copyProperties(department,departmentDto);
        System.out.println(departmentDto +"print departmentdto");
        return departmentDto;
    }

    @Override
    public DepartmentDto findDeptById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(()-> new DepartmentException("Department does not exist",404));

        DepartmentDto departmentDto = new DepartmentDto();

        BeanUtils.copyProperties(department,departmentDto);
        return departmentDto;

    }

    @Override
    public List<Department> findAllDepartment() {
        if(departmentRepository.findAll().isEmpty()){
            throw new DepartmentException("The repository is empty",404);
        }
        return departmentRepository.findAll();
    }
}
