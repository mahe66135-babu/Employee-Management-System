package org.mahesh.ems.mapper;

import org.mahesh.ems.dto.LeaveResponse;
import org.mahesh.ems.entity.LeaveRequest;

public class LeaveMapper {

    public static LeaveResponse toResponse(LeaveRequest leave) {

        return new LeaveResponse(
                leave.getId(),
                leave.getEmployee().getFirstName() + " " +
                        leave.getEmployee().getLastName(),
                leave.getStartDate(),
                leave.getEndDate(),
                leave.getReason(),
                leave.getStatus()
        );
    }
}