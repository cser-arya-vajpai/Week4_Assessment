package com.capgemini.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capgemini.model.entity.Employee;

/**
 * Repository interface for Employee entity.
 * 
 * Extends JpaRepository to provide built-in CRUD operations
 * such as save(), findById(), findAll(), delete(), etc.
 * 
 * Spring Data JPA automatically provides implementation at runtime,
 * so no need to write method implementations manually.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
    // No need to write any code here for basic CRUD operations.
    // Custom query methods can be added if required.
}