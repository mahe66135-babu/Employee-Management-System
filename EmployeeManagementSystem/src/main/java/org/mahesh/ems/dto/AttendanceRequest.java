package org.mahesh.ems.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.mahesh.ems.entity.AttendanceStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AttendanceRequest {

    @NotNull(message = "Employee Id is required")
    private Long employeeId;

    @NotNull(message = "Date is required")
    private LocalDate date;

    private LocalDateTime checkIn;

    private LocalDateTime checkOut;

    @NotNull(message = "Status is required")
    private AttendanceStatus status;
}
