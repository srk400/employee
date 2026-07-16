package com.example.restApi.services;

import com.example.restApi.entity.Employee;
import com.example.restApi.repo.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private  EmployeeRepo employeeRepo;

    @InjectMocks
    private EmployeeService employeeService;


    @Test
    void getAllEmployees() {
        Employee emp1 = new Employee(1L,"siva","s@gmail.com");
        Employee emp2 = new Employee(2L,"rama","r@gmail.com");

        List<Employee> employeeList = Arrays.asList(emp1,emp2);

        when(employeeRepo.findAll()).thenReturn(employeeList);

        ResponseEntity<List<Employee>> response =  employeeService.getAllEmployees();

        assertNotNull(response);
    }

    @Test
    void getEmployeeById() {
        Long id = 1L;
        Employee emp1 = new Employee(1L,"siva","s@gmail.com");

        when(employeeRepo.findById(id)).thenReturn(Optional.of(emp1));

        ResponseEntity<Employee> result = employeeService.getEmployeeById(id);

        assertEquals(emp1,result.getBody());

    }

    @Test
    void getEmployeeByIdNotPresent() {
        Long id = 99L;

        when(employeeRepo.findById(id)).thenReturn(Optional.empty());


        assertThrows(NoSuchElementException.class,() -> employeeService.getEmployeeById(id) );

    }

    @Test
    void saveEmployee() {
        Employee emp1 = new Employee(1L,"siva","s@gmail.com");

        Employee savedEmp = new Employee(1L,"siva","s@gmail.com");


        when(employeeRepo.save(emp1)).thenReturn(savedEmp);

        ResponseEntity<Employee> result = employeeService.saveEmployee(emp1);

        assertEquals(savedEmp,result.getBody());

    }

    @Test
    void updateEmployeeById() {
        Long id = 1L;
        Employee emp = new Employee(1L,"siva","s@gmail.com");

        Employee newEmp = new Employee(null,"siva2","s2@gmail.com");

        Employee savedEmp = new Employee(1L,"siva2","s2@gmail.com");

        when(employeeRepo.findById(id)).thenReturn(Optional.of(emp));
        when(employeeRepo.save(any(Employee.class))).thenReturn(savedEmp);

        ResponseEntity<Employee> result = employeeService.updateEmployeeById(newEmp,id);

        assertEquals(savedEmp.getId(),result.getBody().getId());
    }

    @Test
    void deleteEmployeeById() {
        Long id = 1L;

        doNothing().when(employeeRepo).deleteById(id);

        ResponseEntity<Void> result = employeeService.deleteEmployeeById(id);

        assertNull(result.getBody());
    }
}