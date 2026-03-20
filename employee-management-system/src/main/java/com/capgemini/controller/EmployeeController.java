package com.capgemini.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.model.entity.Employee;
import com.capgemini.model.service.EmployeeService;

/**
 * REST Controller class for handling Employee-related HTTP requests.
 * 
 * This class exposes API endpoints to perform operations such as
 * saving single and multiple employees.
 */
@RestController
public class EmployeeController {

    /**
     * Injecting EmployeeService dependency using Spring's
     * Dependency Injection mechanism.
     */
    @Autowired
    private EmployeeService employeeService;

    /**
     * Handles HTTP POST request to save a single Employee.
     *
     * @param employee the Employee object received in request body (JSON)
     */
    @PostMapping("/saveEmployee")
    public void postEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee); // delegates to service layer
    }

    /**
     * Handles HTTP POST request to save multiple Employees.
     *
     * @param employees list of Employee objects received in request body (JSON array)
     */
    @PostMapping("/saveEmployees")
    public void postEmployees(@RequestBody List<Employee> employees) {
        employeeService.saveEmployees(employees); // delegates to service layer
    }
    
    @GetMapping("/getEmployee/{id}")
    public Employee postEmployees(@PathVariable Integer id) {
        return employeeService.getEmployee(id); 
    }
    
    @GetMapping("/getEmployees")
    public List<Employee> getEmployees() {
    	return employeeService.getEmployees();
    }
    
    @PutMapping("/putEmployee/{id}")
    public Employee putEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
    	return employeeService.updateEmployee(id, employee);
    }
    
    @DeleteMapping("/deleteEmployee/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
		employeeService.deleteEmployee(id);
		
	}
    
    @PatchMapping("/updateEmployeePatch/{id}")
    public void updateEmployeePatch(@PathVariable Integer id, @RequestBody Map<String, Object> fields) {
    	employeeService.updateEmployeePatch(id, fields);
    	
    	
    }
}