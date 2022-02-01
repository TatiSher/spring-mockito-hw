package pro.sky.springmockitohw.service;

import pro.sky.springmockitohw.data.Employee;

import java.util.Collection;

public interface EmployeeService {

    Employee add(String firstName, String lastName, int department, int salary);

    Employee remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);

    Collection<Employee> getEmployees();
}
