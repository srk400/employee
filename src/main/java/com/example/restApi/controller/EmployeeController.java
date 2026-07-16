package com.example.restApi.controller;

import com.example.restApi.entity.Employee;
import com.example.restApi.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(){

        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {

        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){

        return employeeService.saveEmployee(employee);

    }

    @PutMapping("/employees/{id}")
    public void updateEmployeeById(@RequestBody Employee newEmployee,@PathVariable Long id){

        employeeService.updateEmployeeById(newEmployee,id);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployeeById(@PathVariable Long id){

        employeeService.deleteEmployeeById(id);
    }
}
