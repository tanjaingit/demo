package com.airteldemo.demo.Dao;

import com.airteldemo.demo.Model.Employee;

import java.util.List;

public interface EmployeeDao {

    public void add(Employee employee);
    public Employee get(Integer id);
    public List<Employee> getAll();
    public void delete(Integer id);
    public void update(Employee employee);
    public Employee getByAadhar(Integer aadhar);
}
