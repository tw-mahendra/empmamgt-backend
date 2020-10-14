package com.tw.employee.controller;

import com.tw.employee.exception.ResourceNotFoundException;
import com.tw.employee.model.Employee;
import com.tw.employee.model.EmployeeRequest;
import com.tw.employee.services.EmployeesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeesServices employeesServices;

    //get all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeesServices.getAllEmployees();
    }

    //add employeeRequest
    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeesServices.addEmployee(employeeRequest);
    }


    //getemployee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeesServices(@PathVariable String id) throws ResourceNotFoundException {
       return ResponseEntity.ok(employeesServices.getEmployeeById(id));
    }


    //update employee

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody EmployeeRequest employeeRequest) throws ResourceNotFoundException {
        return ResponseEntity.ok(employeesServices.updateEmployee(id,employeeRequest));
    }

    ///delete
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> removeEmployee(@PathVariable String id) throws ResourceNotFoundException {
        return ResponseEntity.ok(employeesServices.removeEmployee(id));
    }
}
