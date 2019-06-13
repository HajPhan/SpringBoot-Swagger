package com.example.springbootjpaswagger2.service;

import com.example.springbootjpaswagger2.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeesById(int id);
    void saveEmployee(Employee employee);
    void deleteEmployeeById(int id);
}
