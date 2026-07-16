package com.example.restApi.services;

import com.example.restApi.entity.Employee;
import com.example.restApi.repo.EmployeeRepo;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees = employeeRepo.findAll();

        return ResponseEntity.ok(employees);
    }

    public ResponseEntity<Employee> getEmployeeById(Long id){
        Optional<Employee> employee =  employeeRepo.findById(id);
        Employee employee1 =  employee.get();
        return ResponseEntity.ok(employee1);
    }

    public ResponseEntity<Employee> saveEmployee(Employee employee){
        Employee saveEmployee = employeeRepo.save(employee);
        return ResponseEntity.ok(saveEmployee);
    }

    public ResponseEntity<Employee> updateEmployeeById(Employee newEmployee, Long id){
        Optional<Employee> oldEmployee = employeeRepo.findById(id);
        Employee updateEmployee = oldEmployee.get();
        updateEmployee.setEmail(newEmployee.getEmail());
        updateEmployee.setName(newEmployee.getName());

        Employee updatedEmployee = employeeRepo.save(updateEmployee);
      return ResponseEntity.ok(updatedEmployee);

    }

    public ResponseEntity<Void> deleteEmployeeById(Long id){

        employeeRepo.deleteById(id);
        return ResponseEntity.ok().build();

    }
}
