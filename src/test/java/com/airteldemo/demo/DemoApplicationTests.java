package com.airteldemo.demo;

import com.airteldemo.demo.Exception.BadRequestException;
import com.airteldemo.demo.Model.Employee;
import com.airteldemo.demo.Service.EmployeeService;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private EmployeeService employeeService;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	void contextLoads() {
	}

	@Test
	public void testEmployeeCrud(){
		Employee emp = new Employee();
		emp.setId(18);
		emp.setName("Akshat");
		emp.setSalary(183245L);
		emp.setAge(28);
		emp.setAadhar(1791);

		employeeService.addEmployee(emp);
		var savedEntity = employeeService.getEmployeeById(emp.getId());

		emp.setName("Shobit");
		employeeService.updateEmployee(emp);

		employeeService.deleteEmployeeById(emp.getId());
	}

	@Test()
	public void testValidations(){
		Employee emp = new Employee();
		emp.setId(18);
		emp.setName("Akshat");
		emp.setSalary(183245L);
		emp.setAge(28);
		emp.setAadhar(1791);

		validateRegexRejection(emp);
		validateMissingAadharValidation(emp);
		validateEmployeeAlreadyExists(emp);

		employeeService.deleteEmployeeById(emp.getId());
	}

	private void validateMissingAadharValidation(Employee employee){
		employee.setAadhar(null);

		BadRequestException thrown = Assertions.assertThrows(
				BadRequestException.class,
				() -> employeeService.addEmployee(employee),
				"Expected addEmployee() to throw a BadRequestException, but it didn't"
		);

		Assertions.assertTrue(thrown.getMessage().contains("MISSING AADHAR NUMBER"));
	}

	private void validateEmployeeAlreadyExists(Employee employee){
		employee.setAadhar(1800);

		BadRequestException thrown = Assertions.assertThrows(
				BadRequestException.class,
				() -> employeeService.addEmployee(employee),
				"Expected addEmployee() to throw a BadRequestException, but it didn't"
		);

		Assertions.assertTrue(thrown.getMessage().contains("AADHAR ALREADY EXISTS"));
	}

	private void validateRegexRejection(Employee employee){
		employee.setName("Aarya<>");

		BadRequestException thrown = Assertions.assertThrows(
				BadRequestException.class,
				() -> employeeService.addEmployee(employee),
				"Expected addEmployee() to throw a BadRequestException, but it didn't"
		);
		Assertions.assertTrue(thrown.getMessage().contains("INVALID INPUT"));
	}

}
