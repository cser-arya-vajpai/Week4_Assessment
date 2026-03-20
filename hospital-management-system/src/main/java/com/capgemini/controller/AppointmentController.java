package com.capgemini.controller;

import com.capgemini.dto.AppointmentDTO;
import com.capgemini.model.Appointment;
import com.capgemini.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctors")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    @PostMapping("/{doctorId}/appointments")
    public Appointment create(@PathVariable Long doctorId,
                              @RequestBody AppointmentDTO dto) {
        return service.createAppointment(doctorId, dto);
    }
}