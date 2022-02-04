package pro.sky.springmockitohw.service;

import pro.sky.springmockitohw.data.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentInfoService {

    Employee getEmployeeWithMaxSalary(int department);

    Employee getEmployeeWithMinSalary(int department);

    Collection<Employee> getEmployeesByDepartment(int department);

    Map<Integer, List<Employee>> getAllEmployees();
}
