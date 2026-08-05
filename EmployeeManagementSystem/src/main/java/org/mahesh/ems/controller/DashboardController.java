package org.mahesh.ems.controller;

import lombok.RequiredArgsConstructor;
import org.mahesh.ems.dto.DashboardResponse;
import org.mahesh.ems.service.DashboardService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public DashboardResponse getDashboard() {

        return dashboardService.getDashboard();
    }

}
