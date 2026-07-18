package mappers;

import com.example.restApi.dto.EmployeeDto;
import com.example.restApi.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeDto toEmployeeDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getName(),
                employee.getEmail()
        );
    }
}
