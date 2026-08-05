package org.mahesh.ems.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "attendance")
@Data
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private LocalDate attendanceDate;

    private LocalTime checkIn;

    private LocalTime checkOut;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    private Double workingHours;

}