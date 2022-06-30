package com.airteldemo.demo.Service;


import com.airteldemo.demo.Model.Employee;

import java.util.List;

public interface EmployeeService {

    public void addEmployee(Employee employee);
    public Employee getEmployeeById(Integer id);
    public List<Employee> getAllEmployee();
    public void deleteEmployeeById(Integer id);
    public Employee updateEmployee(Employee employee);
}
