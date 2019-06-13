package com.example.springbootjpaswagger2.repository;

import com.example.springbootjpaswagger2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepsitory extends CrudRepository<Employee, Integer> {

}
