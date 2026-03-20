package com.capgemini.controller;

import com.capgemini.dto.DoctorDTO;
import com.capgemini.model.Doctor;
import com.capgemini.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @PostMapping
    public Doctor create(@RequestBody DoctorDTO dto) {
        return service.createDoctor(dto);
    }

    @GetMapping
    public List<Doctor> getAll() {
        return service.getAllDoctors();
    }

    @GetMapping("/{id}")
    public Doctor getById(@PathVariable Long id) {
        return service.getDoctor(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteDoctor(id);
    }
}