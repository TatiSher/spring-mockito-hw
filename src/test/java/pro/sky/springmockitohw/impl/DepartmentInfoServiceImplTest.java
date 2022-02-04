package pro.sky.springmockitohw.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.springmockitohw.data.Employee;
import pro.sky.springmockitohw.exceptions.NoEmployeeFoundException;
import pro.sky.springmockitohw.service.EmployeeService;

import java.util.Collection;
import java.util.List;
import java.util.Map;


import static pro.sky.springmockitohw.impl.EmployeeServiceTestConstants.*;
import static pro.sky.springmockitohw.impl.EmployeeServiceTestConstants.SALARY_1;;

@ExtendWith(MockitoExtension.class)
public class DepartmentInfoServiceImplTest {

    @Mock
    private EmployeeService employeeServiceMock;

    @InjectMocks
    private DepartmentInfoServiceImpl out;

    @BeforeEach
    public void settings() {
        Mockito.when(employeeServiceMock.getEmployees()).thenReturn(List.of(
                new Employee(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1),
                new Employee(FIRST_NAME_3, LAST_NAME_3, DEPARTMENT_1, SALARY_3)));
    }

    @Test
    public void testGetEmployeeWithMaxSalary() {
        Employee employee = out.getEmployeeWithMaxSalary(DEPARTMENT_1);
        Employee expectedEmployee = new Employee(FIRST_NAME_3, LAST_NAME_3, DEPARTMENT_1, SALARY_3);
        Assertions.assertEquals(expectedEmployee, employee);
    }

    @Test
    public void testGetEmployeeWithMaxSalaryExceptionCheck() {
        Assertions.assertThrows(NoEmployeeFoundException.class, () -> out.getEmployeeWithMaxSalary(DEPARTMENT_2));
    }

    @Test
    public void testGetEmployeeWithMinSalary() {
        Employee employee = out.getEmployeeWithMinSalary(DEPARTMENT_1);
        Employee expectedResult = new Employee(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1);
        Assertions.assertEquals(expectedResult, employee);
    }

    @Test
    public void testGetEmployeeWithMinSalaryExceptionCheck() {
        Assertions.assertThrows(NoEmployeeFoundException.class, () -> out.getEmployeeWithMinSalary(DEPARTMENT_2));
    }

    @Test
    public void testGetEmployeesByDepartment() {
        Collection<Employee> employee = out.getEmployeesByDepartment(DEPARTMENT_1);
        List<Employee> expectedEmployee = List.of(new Employee(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1),
                                        new Employee(FIRST_NAME_3, LAST_NAME_3, DEPARTMENT_1, SALARY_3));
        Assertions.assertEquals(expectedEmployee,employee);
        Assertions.assertTrue(expectedEmployee.containsAll(employee));
    }
}