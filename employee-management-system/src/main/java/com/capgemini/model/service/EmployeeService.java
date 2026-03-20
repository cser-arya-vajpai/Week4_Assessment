package com.capgemini.model.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.capgemini.model.repository.EmployeeRepository;
import com.capgemini.model.entity.Employee;

import java.util.Map.Entry;


/**
 * Service layer class responsible for handling business logic related to
 * Employee operations.
 * 
 * Acts as an intermediate layer between Controller and Repository.
 */
@Service
public class EmployeeService {

	/**
	 * Injecting EmployeeRepository dependency using Spring's Dependency Injection
	 * mechanism.
	 */
	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * Saves a single Employee entity to the database.
	 *
	 * @param employee the Employee object to be persisted
	 */
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee); // persists employee data
		System.out.println("Employee saved successfully!");
	}

	/**
	 * Saves a list of Employee entities to the database in bulk.
	 *
	 * @param employees list of Employee objects to be persisted
	 */
	public void saveEmployees(List<Employee> employees) {
		employeeRepository.saveAll(employees); // batch save operation
		System.out.println("Employees saved successfully.");
	}

	public Employee getEmployee(Integer id) {
		return employeeRepository.findById(id).get();
	}

	public List<Employee> getEmployees() {
		return employeeRepository.findAll();

	}

	public Employee updateEmployee(Integer id, Employee newEmployee) {
		Employee existing = employeeRepository.findById(id).get();
		if (newEmployee != null) {
			if (existing != null) {
				existing.setName(newEmployee.getName());
				existing.setEmail(newEmployee.getEmail());
				existing.setPhone(newEmployee.getPhone());
			}
		}
		return employeeRepository.save(existing);

	}
	
	public void deleteEmployee(Integer id) {
		Employee existing =  employeeRepository.findById(id).get();
		employeeRepository.deleteById(id);
	}
	
	//DO ONE FOR DELETE ALL 
	
	
	public void updateEmployeePatch(Integer id, Map<String, Object> fields) {
		Employee existing = employeeRepository.findById(id).get();
		
		for(Map.Entry<String, Object> field : fields.entrySet()) {
			String key = field.getKey();
			Object value = field.getValue();
			
			if(key.equals("name")) {
				existing.setName((String)value);
			}
			if(key.equals("email")) {
				existing.setEmail((String)value);
			}
			employeeRepository.save(existing);
			
		}
		
	}
}