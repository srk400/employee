package com.example.restApi.controller;

import com.example.restApi.dto.EmployeeDto;
import com.example.restApi.entity.Employee;
import com.example.restApi.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeeDto employeeDto) {

        Employee employee = employeeService.saveEmployee(employeeDto);

        return ResponseEntity.ok(employee);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {

        List<EmployeeDto> enployeeList = employeeService.getAllEmployees();

        return ResponseEntity.ok(enployeeList);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {

        EmployeeDto employeeDto = employeeService.getEmployeeById(id);

        return ResponseEntity.ok(employeeDto);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@RequestBody Employee employeeDto, @PathVariable Long id) {

        EmployeeDto updatedEmployeeDto = employeeService.updateEmployeeById(employeeDto, id);

        return ResponseEntity.ok(updatedEmployeeDto);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id) {

        employeeService.deleteEmployeeById(id);

        return ResponseEntity.noContent().build();
    }
}
