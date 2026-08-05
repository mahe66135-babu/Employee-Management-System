package org.mahesh.ems.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mahesh.ems.dto.LeaveRequestDto;
import org.mahesh.ems.dto.LeaveResponse;
import org.mahesh.ems.dto.LeaveStatisticsResponse;
import org.mahesh.ems.entity.LeaveStatus;
import org.mahesh.ems.service.LeaveService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LeaveController {

    private final LeaveService leaveService;

    // Employee Apply Leave
    @PostMapping
    @PreAuthorize("hasRole('EMPLOYEE')")
    public LeaveResponse applyLeave(
            Authentication authentication,
            @Valid @RequestBody LeaveRequestDto request) {

        return leaveService.applyLeave(authentication.getName(), request);
    }

    // Employee View Own Leaves
    @GetMapping("/me")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public List<LeaveResponse> getMyLeaves(
            Authentication authentication) {

        return leaveService.getMyLeaves(
                authentication.getName());
    }

    // Admin View All Leaves
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<LeaveResponse> getAllLeaves() {

        return leaveService.getAllLeaves();
    }

    // Admin Approve Leave
    @PutMapping("/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public LeaveResponse approveLeave(
            @PathVariable Long id) {

        return leaveService.approveLeave(id);
    }

    // Admin Reject Leave
    @PutMapping("/{id}/reject")
    @PreAuthorize("hasRole('ADMIN')")
    public LeaveResponse rejectLeave(
            @PathVariable Long id) {

        return leaveService.rejectLeave(id);
    }
 //Leave Statistices
    @GetMapping("/statistics")
    @PreAuthorize("hasRole('ADMIN')")
    public LeaveStatisticsResponse getStatistics() {

        return leaveService.getLeaveStatistics();
    }
    @GetMapping("/status")
    @PreAuthorize("hasRole('ADMIN')")
    public List<LeaveResponse> getLeavesByStatus(
            @RequestParam LeaveStatus status) {

        return leaveService.getLeavesByStatus(status);
    }
}