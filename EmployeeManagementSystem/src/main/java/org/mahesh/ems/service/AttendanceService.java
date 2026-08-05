package org.mahesh.ems.service;

import org.mahesh.ems.dto.AttendanceResponse;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    AttendanceResponse checkIn(String email);

    AttendanceResponse checkOut(String email);

    List<AttendanceResponse> getMyAttendance(String email);

    List<AttendanceResponse> getAllAttendance();

    List<AttendanceResponse> getAttendanceByDate(LocalDate date);

}