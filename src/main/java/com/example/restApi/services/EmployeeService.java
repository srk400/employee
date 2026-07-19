package com.example.restApi.services;

import com.example.restApi.dto.EmployeeDto;
import com.example.restApi.entity.Employee;
import com.example.restApi.mappers.EmployeeMapper;
import com.example.restApi.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;

    public Employee saveEmployee(EmployeeDto employeeDto) {

        Employee employee = new Employee();

        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());

        return employeeRepo.save(employee);
    }

    public List<EmployeeDto> getAllEmployees() {

        List<Employee> employeeList = employeeRepo.findAll();

        return employeeList.stream()
                .map(employeeMapper::toEmployeeDto)
                .toList();
    }

    public EmployeeDto getEmployeeById(Long id) {

        Employee employeeEntity = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee is not found with id:" + id));

        return employeeMapper.toEmployeeDto(employeeEntity);
    }

    public EmployeeDto updateEmployeeById(Employee employeeDto, Long id) {

        Employee employeeEntity = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("No Employee with id:" + id));

        employeeEntity.setEmail(employeeDto.getEmail());
        employeeEntity.setName(employeeDto.getName());

        Employee updatedEmployee = employeeRepo.save(employeeEntity);

        return new EmployeeDto(
                updatedEmployee.getId(),
                updatedEmployee.getName(),
                updatedEmployee.getEmail()
        );
    }

    public void deleteEmployeeById(Long id) {

        employeeRepo.deleteById(id);
    }
}
