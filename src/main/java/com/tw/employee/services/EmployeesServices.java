package com.tw.employee.services;

import com.tw.employee.exception.ResourceNotFoundException;
import com.tw.employee.model.Employee;
import com.tw.employee.model.EmployeeRequest;
import com.tw.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class EmployeesServices {

    @Autowired
    EmployeeRepository employeeRepository;


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        Employee employee = Employee.builder().emailId(employeeRequest.getEmailId())
                .lastName(employeeRequest.getLastName())
                .firstName(employeeRequest.getFirstName())
                .id(UUID.randomUUID().toString()).build();
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(String id) throws ResourceNotFoundException {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee exist with id " + id));
    }

    public Employee updateEmployee(String id, EmployeeRequest employeeRequest) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee exist with id " + id));
        employee.setEmailId(employeeRequest.getEmailId());
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employeeRepository.save(employee);
        return employee;
    }

    public Map<String, Boolean> removeEmployee(String id) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee exist with id " + id));
        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
