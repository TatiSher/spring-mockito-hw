package pro.sky.springmockitohw.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sky.springmockitohw.data.Employee;
import pro.sky.springmockitohw.exceptions.EmployeeExistsException;
import pro.sky.springmockitohw.exceptions.NoEmployeeFoundException;
import pro.sky.springmockitohw.service.EmployeeService;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.springmockitohw.impl.EmployeeServiceTestConstants.*;

class EmployeeServiceImplTest {

    private EmployeeService out = new EmployeeServiceImpl();

    @Test
    public void testAdd() {
        Employee employee = out.add(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1);
        Employee expectedEmployee = new Employee(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1);
        Assertions.assertEquals(expectedEmployee, employee);
    }

    @Test
    public void testAddExceptionCheck() {
        out.add(FIRST_NAME_2, LAST_NAME_2, DEPARTMENT_2, SALARY_2);
        Assertions.assertThrowsExactly(EmployeeExistsException.class, () -> out.add(FIRST_NAME_2, LAST_NAME_2, DEPARTMENT_2, SALARY_2));
    }

    @Test
    public void testRemove() {
        out.add(FIRST_NAME_2, LAST_NAME_2, DEPARTMENT_2, SALARY_2);
        Employee employee = out.remove(FIRST_NAME_2,LAST_NAME_2);
        Employee expectedEmployee = new Employee(FIRST_NAME_2, LAST_NAME_2);
        Assertions.assertEquals(expectedEmployee, employee);
    }

    @Test
    public void testRemoveExceptionCheck() {
        out.add(FIRST_NAME_2, LAST_NAME_2, DEPARTMENT_2, SALARY_2);
        Assertions.assertThrowsExactly(NoEmployeeFoundException.class, () -> out.remove(FIRST_NAME_2, LAST_NAME_2));
    }

    @Test
    public void testFind() {
        out.add(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1);
        Employee employee = out.find(FIRST_NAME_1, LAST_NAME_1);
        Employee expectedEmployee = new Employee(FIRST_NAME_1, LAST_NAME_1);
        Assertions.assertEquals(expectedEmployee, employee);
    }

    @Test
    public void testFindExceptionCheck() {
        Assertions.assertThrowsExactly(NoEmployeeFoundException.class, () -> out.find(FIRST_NAME_2, LAST_NAME_2));
    }
}