package com.airteldemo.demo.Mapper;

import com.airteldemo.demo.Model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {

        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee emp = new Employee();
            emp.setId(rs.getInt("id"));
            emp.setName(rs.getString("name"));
            emp.setSalary(rs.getLong("salary"));
            emp.setAge(rs.getInt("age"));
            emp.setAadhar(rs.getInt("id"));
            emp.setDob(rs.getDate("dob"));
            return emp;
        }

}
