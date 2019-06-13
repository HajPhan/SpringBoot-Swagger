package com.example.springbootjpaswagger2.service;

import com.example.springbootjpaswagger2.model.Employee;
import com.example.springbootjpaswagger2.repository.EmployeeRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepsitory employeeRepsitory;

    @Override
    public List<Employee> getAllEmployees() {
        return (List<Employee>) this.employeeRepsitory.findAll();
    }

    @Override
    public Employee getEmployeesById(int id) {
        return this.employeeRepsitory.findById(id).get();
    }

    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepsitory.save(employee);
    }

    @Override
    public void deleteEmployeeById(int id) {
        this.employeeRepsitory.deleteById(id);
    }
}
