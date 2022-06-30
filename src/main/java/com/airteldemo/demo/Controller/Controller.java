package com.airteldemo.demo.Controller;

import com.airteldemo.demo.Model.Employee;
import com.airteldemo.demo.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/employee")
public class Controller {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public void Add(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
    }

    @GetMapping("/get")
    public Employee Get(@RequestParam Integer id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/getAll")
    public List<Employee> GetAll(){
        return employeeService.getAllEmployee();
    }

    @DeleteMapping("/delete")
    public void Delete(@RequestParam Integer id){
        employeeService.deleteEmployeeById(id);
    }

    @PutMapping("/update")
    public Employee Update(@RequestBody Employee employee){
        return employeeService.updateEmployee(employee);
    }
}
