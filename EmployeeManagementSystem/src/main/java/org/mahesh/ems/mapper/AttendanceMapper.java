package org.mahesh.ems.mapper;

import org.mahesh.ems.dto.AttendanceResponse;
import org.mahesh.ems.entity.Attendance;

public class AttendanceMapper {

    public static AttendanceResponse toResponse(
            Attendance attendance) {

        return new AttendanceResponse(

                attendance.getId(),

                attendance.getEmployee().getFirstName()
                        + " "
                        + attendance.getEmployee().getLastName(),

                attendance.getAttendanceDate(),

                attendance.getCheckIn(),

                attendance.getCheckOut(),

                attendance.getStatus(),

                attendance.getWorkingHours()

        );
    }

}