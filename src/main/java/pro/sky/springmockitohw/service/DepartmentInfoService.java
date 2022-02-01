package pro.sky.springmockitohw.service;

import pro.sky.springmockitohw.data.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentInfoService {

    Employee getEmployeeWithMaxSalary(int department);

    Employee getEmployeeWithMinSalary(int department);

    Map<Integer, List<Employee>> getAllEmployees();

    Map<Integer, List<Employee>> getEmployeesByDepartment(int department);
}
