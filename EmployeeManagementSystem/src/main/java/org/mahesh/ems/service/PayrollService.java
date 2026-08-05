package org.mahesh.ems.service;

import jakarta.servlet.http.HttpServletResponse;
import org.mahesh.ems.dto.PayrollRequest;
import org.mahesh.ems.dto.PayrollResponse;

import java.io.IOException;
import java.util.List;

public interface PayrollService {

    PayrollResponse generatePayroll(PayrollRequest request);

    List<PayrollResponse> getAllPayrolls();

    PayrollResponse getPayrollById(Long id);

    List<PayrollResponse> getMyPayroll(String email);
    void downloadSalarySlip(
            Long id,
            HttpServletResponse response)
            throws IOException;

    void deletePayroll(Long id);
}