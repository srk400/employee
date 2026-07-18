package com.example.restApi.services;

import com.example.restApi.dto.EmployeeDto;
import com.example.restApi.entity.Employee;
import com.example.restApi.repo.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private EmployeeService employeeService;


    @Test
    void saveEmployee() {

        EmployeeDto empDto = new EmployeeDto(1L, "siva", "s@gmail.com");

        Employee savedEmp = new Employee(1L, "siva", "s@gmail.com");

        when(employeeRepo.save(any(Employee.class))).thenReturn(savedEmp);

        Employee result = employeeService.saveEmployee(empDto);

        assertEquals(savedEmp.getId(), result.getId());
        assertEquals(savedEmp.getName(), result.getName());
        assertEquals(savedEmp.getEmail(), result.getEmail());

        verify(employeeRepo).save(any(Employee.class));
    }


    @Test
    void getAllEmployees() {
        Employee emp1 = new Employee(1L, "siva", "s@gmail.com");
        Employee emp2 = new Employee(2L, "rama", "r@gmail.com");

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(emp1);
        employeeList.add(emp2);

        when(employeeRepo.findAll()).thenReturn(employeeList);

        List<EmployeeDto> response = employeeService.getAllEmployees();

        assertNotNull(response);
        assertEquals(2, response.size(), "List size should be 2");
        assertEquals("siva", response.get(0).getName());
        assertEquals("rama", response.get(1).getName());

        verify(employeeRepo).findAll();
    }

    @Test
    void getEmployeeById() {
        Long id = 1L;
        Employee employeeEntity = new Employee(1L, "siva", "s@gmail.com");

        EmployeeDto employeeDto = new EmployeeDto(1L, "siva", "s@gmail.com");

        when(employeeRepo.findById(id)).thenReturn((Optional.of(employeeEntity)));

        EmployeeDto result = employeeService.getEmployeeById(id);

        assertEquals(employeeDto, result);
        assertEquals(employeeEntity.getId(), result.getId());
        assertEquals(employeeEntity.getName(), result.getName());
        assertEquals(employeeEntity.getEmail(), result.getEmail());

        verify(employeeRepo).findById(id);
    }

    @Test
    void getEmployeeByIdNotPresent() {
        Long id = 99L;

        when(employeeRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> employeeService.getEmployeeById(id));

    }

    @Test
    void updateEmployeeById() {
        Long id = 1L;
        Employee emp = new Employee(1L, "siva", "s@gmail.com");

        Employee newEmp = new Employee(null, "siva2", "s2@gmail.com");

        Employee savedEmp = new Employee(1L, "siva2", "s2@gmail.com");

        when(employeeRepo.findById(id)).thenReturn(Optional.of(emp));
        when(employeeRepo.save(any(Employee.class))).thenReturn(savedEmp);

        EmployeeDto result = employeeService.updateEmployeeById(newEmp, id);

        assertEquals(savedEmp.getId(), result.getId());
        assertEquals(savedEmp.getName(), result.getName());
        assertEquals(savedEmp.getEmail(), result.getEmail());

        verify(employeeRepo).findById(id);
        verify(employeeRepo).save(any(Employee.class));
    }

    @Test
    void deleteEmployeeById() {
        
        Long id = 1L;

        doNothing().when(employeeRepo).deleteById(id);

        employeeService.deleteEmployeeById(id);

        verify(employeeRepo).deleteById(id);
    }
}