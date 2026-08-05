package org.mahesh.ems.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.mahesh.ems.dto.PayrollRequest;
import org.mahesh.ems.dto.PayrollResponse;
import org.mahesh.ems.service.PayrollService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/payroll")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PayrollController {

    private final PayrollService payrollService;

    // Generate Payroll (ADMIN)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public PayrollResponse generatePayroll(
            @RequestBody PayrollRequest request) {

        return payrollService.generatePayroll(request);
    }

    // View All Payrolls (ADMIN)
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<PayrollResponse> getAllPayrolls() {

        return payrollService.getAllPayrolls();
    }

    // View Payroll By ID (ADMIN)
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public PayrollResponse getPayrollById(
            @PathVariable Long id) {

        return payrollService.getPayrollById(id);
    }

    // Employee View Own Payroll
    @GetMapping("/me")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public List<PayrollResponse> getMyPayroll(
            Authentication authentication) {

        return payrollService.getMyPayroll(
                authentication.getName());
    }
    @GetMapping("/{id}/pdf")
    @PreAuthorize("hasRole('ADMIN')")
    public void downloadSalarySlip(
            @PathVariable Long id,
            HttpServletResponse response)
            throws IOException {

        response.setContentType("application/pdf");

        response.setHeader(
                "Content-Disposition",
                "attachment; filename=salary-slip.pdf");

        payrollService.downloadSalarySlip(id, response);
    }

    // Delete Payroll (ADMIN)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deletePayroll(
            @PathVariable Long id) {

        payrollService.deletePayroll(id);

        return "Payroll deleted successfully";
    }
}