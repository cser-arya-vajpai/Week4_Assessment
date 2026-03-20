package com.capgemini.model.entity;

import java.io.Serializable;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;

/**
 * Entity class representing the Employee table in the database.
 * 
 * This class is mapped to a database table using JPA annotations.
 * Lombok is used to reduce boilerplate code such as getters,
 * setters, constructors, etc.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Employee {


     
	//@Id → marks id field as primary key
    @Id
    
    // @GeneratedValue → auto-generates value using database identity column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Integer id;
    private String name;
    private String email;
    private long phone;
}



