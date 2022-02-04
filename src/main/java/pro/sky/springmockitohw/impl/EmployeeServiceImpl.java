package pro.sky.springmockitohw.impl;

import org.springframework.stereotype.Service;
import pro.sky.springmockitohw.data.Employee;
import pro.sky.springmockitohw.exceptions.EmployeeExistsException;
import pro.sky.springmockitohw.exceptions.NoEmployeeFoundException;
import pro.sky.springmockitohw.service.EmployeeService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String,Employee> employees;

    public EmployeeServiceImpl() {
        employees = new HashMap<>();
    }

    private String getKey(Employee employee) {
        return getKey(employee.getFirstName(),employee.getLastName());
    }

    private String getKey(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    @Override
    public Employee add(String firstName, String lastName, int department, int salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        String key = getKey(employee);
        if(employees.containsKey(key)) {
            throw new EmployeeExistsException();
        }
        employees.put(key,employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName, int department, int salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        String key = getKey(employee);
        if(!employees.containsKey(key)) {
            throw new NoEmployeeFoundException();
        }
        employees.remove(key);
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        String key = getKey(employee);
        if (!employees.containsKey(key)) {
            throw new NoEmployeeFoundException();
        }
        return employee;
    }

    @Override
    public Collection<Employee> getEmployees(){
        return Set.copyOf(employees.values());
    }
}