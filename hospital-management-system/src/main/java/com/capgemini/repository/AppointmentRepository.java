package com.capgemini.repository;

import com.capgemini.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByStatusOrderByScheduledTimeAsc(String status);
}