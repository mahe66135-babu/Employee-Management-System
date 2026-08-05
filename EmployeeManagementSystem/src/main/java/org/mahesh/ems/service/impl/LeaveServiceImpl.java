package org.mahesh.ems.service.impl;

import lombok.RequiredArgsConstructor;
import org.mahesh.ems.dto.LeaveRequestDto;
import org.mahesh.ems.dto.LeaveResponse;
import org.mahesh.ems.dto.LeaveStatisticsResponse;
import org.mahesh.ems.entity.Employee;
import org.mahesh.ems.entity.LeaveRequest;
import org.mahesh.ems.entity.LeaveStatus;
import org.mahesh.ems.exception.ResourceNotFoundException;
import org.mahesh.ems.mapper.LeaveMapper;
import org.mahesh.ems.repository.EmployeeRepository;
import org.mahesh.ems.repository.LeaveRepository;
import org.mahesh.ems.service.LeaveService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public LeaveResponse applyLeave(String email,
                                    LeaveRequestDto request) {

        Employee employee = employeeRepository
                .findByUserEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        if (request.getStartDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Leave cannot start in the past");
        }
        if (request.getEndDate().isBefore(request.getStartDate())) {
            throw new RuntimeException("End date must be after start date");
        }

        boolean exists =
                leaveRepository.existsByEmployeeIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                        employee.getId(),
                        request.getEndDate(),
                        request.getStartDate());

        if (exists) {
            throw new RuntimeException("Leave already exists for these dates");
        }


        LeaveRequest leave = new LeaveRequest();

        leave.setEmployee(employee);
        leave.setStartDate(request.getStartDate());
        leave.setEndDate(request.getEndDate());
        leave.setReason(request.getReason());

        // Default Status
        leave.setStatus(LeaveStatus.PENDING);

        LeaveRequest saved = leaveRepository.save(leave);

        return LeaveMapper.toResponse(saved);
    }

    @Override
    public List<LeaveResponse> getMyLeaves(String email) {

        Employee employee = employeeRepository
                .findByUserEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        return leaveRepository.findByEmployeeId(employee.getId())
                .stream()
                .map(LeaveMapper::toResponse)
                .toList();
    }

    @Override
    public List<LeaveResponse> getAllLeaves() {

        return leaveRepository.findAll()
                .stream()
                .map(LeaveMapper::toResponse)
                .toList();
    }

    @Override
    public LeaveResponse approveLeave(Long id) {

        LeaveRequest leave = leaveRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Leave not found"));

        // ADD HERE
        if (leave.getStatus() != LeaveStatus.PENDING) {
            throw new RuntimeException("Leave request has already been processed");
        }

        leave.setStatus(LeaveStatus.APPROVED);

        LeaveRequest saved = leaveRepository.save(leave);

        return LeaveMapper.toResponse(saved);
    }

    @Override
    public LeaveResponse rejectLeave(Long id) {

        LeaveRequest leave = leaveRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Leave not found"));

        // ADD HERE
        if (leave.getStatus() != LeaveStatus.PENDING) {
            throw new RuntimeException("Leave request has already been processed");
        }

        leave.setStatus(LeaveStatus.REJECTED);

        LeaveRequest saved = leaveRepository.save(leave);

        return LeaveMapper.toResponse(saved);
    }

    @Override
    public LeaveStatisticsResponse getLeaveStatistics() {

        return new LeaveStatisticsResponse(
                leaveRepository.count(),
                leaveRepository.countByStatus(LeaveStatus.PENDING),
                leaveRepository.countByStatus(LeaveStatus.APPROVED),
                leaveRepository.countByStatus(LeaveStatus.REJECTED)
        );
    }

    @Override
    public List<LeaveResponse> getLeavesByStatus(LeaveStatus status) {

        return leaveRepository.findByStatus(status)
                .stream()
                .map(LeaveMapper::toResponse)
                .toList();
    }
}