package com.capgemini.dto;

import lombok.Data;
import jakarta.validation.constraints.Future;

import java.time.LocalDateTime;

@Data
public class AppointmentDTO {

    private String patientName;

    @Future
    private LocalDateTime scheduledTime;

    private String status;
}