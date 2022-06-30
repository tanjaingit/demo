package com.airteldemo.demo.Dao;

import com.airteldemo.demo.Mapper.EmployeeMapper;
import com.airteldemo.demo.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    final String INSERT_QUERY = "insert into employee (id, name, salary, age, aadhar, dob) values (?, ?, ?, ?, ?, ?)";
    final String UPDATE_QUERY = "update employee set name = ?, salary = ?, age = ?, aadhar = ?, dob = ? where id = ?";
    final String DELETE_QUERY = "delete from employee where id = ?";
    final String SELECT_QUERY = "select * from employee where id = ?";
    final String SELECT_ALL_QUERY = "select * from employee";
    final String SELECT_BY_AADHAR = "select * from employee where aadhar = ?";
    final String IF_AADHAR_EXISTS = "select exists(select from employee where aadhar = ?)";

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void add(Employee employee) {
        jdbcTemplate.update(INSERT_QUERY, employee.getId(), employee.getName(), employee.getSalary(),
                employee.getAge(), employee.getAadhar(), employee.getDob());
    }

    @Override
    public Employee get(Integer id) {
        return jdbcTemplate.queryForObject(SELECT_QUERY, new EmployeeMapper(), id);
    }

    @Override
    public List<Employee> getAll() {
        return jdbcTemplate.query(SELECT_ALL_QUERY, new EmployeeMapper());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(DELETE_QUERY, id);
    }

    @Override
    public void update(Employee employee) {
        jdbcTemplate.update(UPDATE_QUERY, employee.getName(), employee.getSalary(),
                employee.getAge(), employee.getAadhar(), employee.getDob(), employee.getId());
    }

    @Override
    public Employee getByAadhar(Integer aadhar) {
        boolean exists = jdbcTemplate.queryForObject(IF_AADHAR_EXISTS, Boolean.class, aadhar);
        if(exists) {
            return jdbcTemplate.queryForObject(SELECT_BY_AADHAR, new EmployeeMapper(), aadhar);
        }else{
            return null;
        }
    }
}
