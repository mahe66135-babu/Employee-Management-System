package org.mahesh.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mahesh.ems.entity.AttendanceStatus;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceResponse {

    private Long id;

    private String employeeName;

    private LocalDate attendanceDate;

    private LocalTime checkIn;

    private LocalTime checkOut;

    private AttendanceStatus status;

    private Double workingHours;

}