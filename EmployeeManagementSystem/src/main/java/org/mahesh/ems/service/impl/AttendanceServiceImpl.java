package org.mahesh.ems.service.impl;

import lombok.RequiredArgsConstructor;
import org.mahesh.ems.dto.AttendanceResponse;
import org.mahesh.ems.entity.*;
import org.mahesh.ems.exception.ResourceNotFoundException;
import org.mahesh.ems.mapper.AttendanceMapper;
import org.mahesh.ems.repository.AttendanceRepository;
import org.mahesh.ems.repository.EmployeeRepository;
import org.mahesh.ems.service.AttendanceService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public AttendanceResponse checkIn(String email) {

        Employee employee = employeeRepository
                .findByUserEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        if (attendanceRepository.existsByEmployeeIdAndAttendanceDate(
                employee.getId(),
                LocalDate.now())) {

            throw new RuntimeException(
                    "Already checked in today");
        }

        Attendance attendance = new Attendance();

        attendance.setEmployee(employee);

        attendance.setAttendanceDate(LocalDate.now());

        attendance.setCheckIn(LocalTime.now());

        attendance.setStatus(AttendanceStatus.PRESENT);

        Attendance saved =
                attendanceRepository.save(attendance);

        return AttendanceMapper.toResponse(saved);
    }

    @Override
    public AttendanceResponse checkOut(String email) {

        Attendance attendance = attendanceRepository
                .findByEmployeeUserEmailAndAttendanceDate(
                        email,
                        LocalDate.now()
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException("Check-in not found"));

        if (attendance.getCheckOut() != null) {

            throw new RuntimeException("Already checked out today");
        }

        attendance.setCheckOut(LocalTime.now());

        Duration duration = Duration.between(
                attendance.getCheckIn(),
                attendance.getCheckOut());

        double hours = duration.toMinutes() / 60.0;

        attendance.setWorkingHours(hours);

        Attendance updated = attendanceRepository.save(attendance);

        return AttendanceMapper.toResponse(updated);
    }
    @Override
    public List<AttendanceResponse> getMyAttendance(String email) {

        return attendanceRepository.findByEmployeeUserEmail(email)
                .stream()
                .map(AttendanceMapper::toResponse)
                .toList();
    }
    @Override
    public List<AttendanceResponse> getAllAttendance() {

        return attendanceRepository.findAll()
                .stream()
                .map(AttendanceMapper::toResponse)
                .toList();
    }
    @Override
    public List<AttendanceResponse> getAttendanceByDate(LocalDate date) {

        return attendanceRepository.findByAttendanceDate(date)
                .stream()
                .map(AttendanceMapper::toResponse)
                .toList();
    }

}