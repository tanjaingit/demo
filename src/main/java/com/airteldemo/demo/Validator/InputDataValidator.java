package com.airteldemo.demo.Validator;

import com.airteldemo.demo.Dao.EmployeeDao;
import com.airteldemo.demo.Exception.BadRequestException;
import com.airteldemo.demo.Model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InputDataValidator {

    @Autowired
    private EmployeeDao employeeDao;

    public void validateInputData(Employee employee){
        // single function in case other validations are to be added later
        validateAadhar(employee.getAadhar());
        checkForRegex(employee);
        validateNotNull(employee);
    }

    private void validateAadhar(Integer aadhar){
        if(aadhar==null){
            throw new BadRequestException( "MISSING AADHAR NUMBER");
        }
        if(employeeDao.getByAadhar(aadhar)!=null){
            throw new BadRequestException( "AADHAR ALREADY EXISTS");
        }
    }

    private void checkForRegex(Employee employee)
    {
        ObjectMapper obj = new ObjectMapper();
        Map<String,Object> props = obj.convertValue(employee, Map.class);

        for (Map.Entry<String, Object> m : props.entrySet()) {
            if (m.getValue() instanceof String) {
                // check for regex
                if (!((String) m.getValue()).matches("[a-zA-Z0-9_+\\-* !@#$%^&(),.|]+")){
                    throw new BadRequestException("INVALID INPUT");
                }
            }
        }
    }

    private void validateNotNull(Employee employee)
    {
        ObjectMapper obj = new ObjectMapper();
        Map<String,Object> props = obj.convertValue(employee, Map.class);

        for (Map.Entry<String, Object> m : props.entrySet()) {
            if (m.getValue() instanceof String) {
                if (m.getValue() == null){
                    throw new BadRequestException("INPUT IS NULL");
                }
            }
        }
    }

}
