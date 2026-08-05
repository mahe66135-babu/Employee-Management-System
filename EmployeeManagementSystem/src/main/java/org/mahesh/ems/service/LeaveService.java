package org.mahesh.ems.service;

import org.mahesh.ems.dto.LeaveRequestDto;
import org.mahesh.ems.dto.LeaveResponse;
import org.mahesh.ems.dto.LeaveStatisticsResponse;
import org.mahesh.ems.entity.LeaveStatus;

import java.util.List;

public interface LeaveService {

    LeaveResponse applyLeave(String email, LeaveRequestDto request);

    List<LeaveResponse> getMyLeaves(String email);

    List<LeaveResponse> getAllLeaves();

    LeaveResponse approveLeave(Long id);

    LeaveResponse rejectLeave(Long id);

    LeaveStatisticsResponse getLeaveStatistics();
    List<LeaveResponse> getLeavesByStatus(LeaveStatus status);
}