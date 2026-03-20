package com.capgemini.service;

import com.capgemini.dto.AppointmentDTO;
import com.capgemini.model.Appointment;
import com.capgemini.model.Doctor;
import com.capgemini.repository.AppointmentRepository;
import com.capgemini.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository apptRepo;

    @Autowired
    private DoctorRepository doctorRepo;

    public Appointment createAppointment(Long doctorId, AppointmentDTO dto) {

        Doctor doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Appointment appt = new Appointment();
        appt.setPatientName(dto.getPatientName());
        appt.setScheduledTime(dto.getScheduledTime());
        appt.setStatus(dto.getStatus());
        appt.setDoctor(doctor);

        return apptRepo.save(appt);
    }
}