package org.mahesh.ems.controller;

import lombok.RequiredArgsConstructor;
import org.mahesh.ems.dto.AttendanceResponse;
import org.mahesh.ems.service.AttendanceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AttendanceController {

    private final AttendanceService attendanceService;

    // Employee Check In
    @PostMapping("/check-in")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public AttendanceResponse checkIn(
            Authentication authentication) {

        return attendanceService.checkIn(authentication.getName());
    }

    // Employee Check Out
    @PutMapping("/check-out")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public AttendanceResponse checkOut(
            Authentication authentication) {

        return attendanceService.checkOut(authentication.getName());
    }

    // Employee View Own Attendance
    @GetMapping("/me")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public List<AttendanceResponse> myAttendance(
            Authentication authentication) {

        return attendanceService.getMyAttendance(authentication.getName());
    }

    // Admin View All Attendance
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<AttendanceResponse> allAttendance() {

        return attendanceService.getAllAttendance();
    }

    // Admin Search Attendance by Date
    @GetMapping("/date")
    @PreAuthorize("hasRole('ADMIN')")
    public List<AttendanceResponse> attendanceByDate(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {

        return attendanceService.getAttendanceByDate(date);
    }
}