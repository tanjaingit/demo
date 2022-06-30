package com.airteldemo.demo.Service;

import com.airteldemo.demo.Dao.EmployeeDao;
import com.airteldemo.demo.Model.Employee;
import com.airteldemo.demo.Validator.InputDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private InputDataValidator dataValidator;

    @Override
    public void addEmployee(Employee employee) {
        dataValidator.validateInputData(employee);
        employeeDao.add(employee);
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeDao.get(id);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeDao.getAll();
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        employeeDao.delete(id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        if(employee.getId()==null){
            return null;
        }
        employeeDao.update(employee);
        return getEmployeeById(employee.getId());
    }
}
