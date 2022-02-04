package pro.sky.springmockitohw.impl;

import org.springframework.stereotype.Service;
import pro.sky.springmockitohw.data.Employee;
import pro.sky.springmockitohw.exceptions.NoEmployeeFoundException;
import pro.sky.springmockitohw.service.DepartmentInfoService;
import pro.sky.springmockitohw.service.EmployeeService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentInfoServiceImpl implements DepartmentInfoService {

    private final EmployeeService employeeService;

    public DepartmentInfoServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmployeeWithMaxSalary(int department) {
        return employeeService.getEmployees().stream()
                .filter(employee -> employee.getDepartment(department))
                .max(Comparator.comparing(employee -> employee.getSalary()))
                .orElseThrow(() -> new NoEmployeeFoundException());
    }

    @Override
    public Employee getEmployeeWithMinSalary(int department) {
        return employeeService.getEmployees().stream()
                .filter(employee -> employee.getDepartment(department))
                .min(Comparator.comparing(employee -> employee.getSalary()))
                .orElseThrow(() -> new NoEmployeeFoundException());
    }

    @Override
    public Collection<Employee> getEmployeesByDepartment(int department) {
        return (Collection<Employee>) employeeService.getEmployees().stream()
                .filter(employee -> employee.getDepartment(department))
                .collect(Collectors.toList());

    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployees() {
        return employeeService.getEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

    }

}
