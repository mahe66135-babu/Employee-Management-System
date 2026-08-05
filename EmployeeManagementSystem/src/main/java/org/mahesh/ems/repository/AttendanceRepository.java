package org.mahesh.ems.repository;

import org.mahesh.ems.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository
        extends JpaRepository<Attendance, Long> {

    Optional<Attendance> findByEmployeeUserEmailAndAttendanceDate(
            String email,
            LocalDate attendanceDate
    );

    List<Attendance> findByEmployeeUserEmail(String email);

    List<Attendance> findByAttendanceDate(LocalDate attendanceDate);

    boolean existsByEmployeeIdAndAttendanceDate(
            Long employeeId,
            LocalDate attendanceDate
    );

}