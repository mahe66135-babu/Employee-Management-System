package org.mahesh.ems.service.impl;

import lombok.RequiredArgsConstructor;
import org.mahesh.ems.dto.PayrollRequest;
import org.mahesh.ems.dto.PayrollResponse;
import org.mahesh.ems.entity.Employee;
import org.mahesh.ems.entity.Payroll;
import org.mahesh.ems.exception.ResourceNotFoundException;
import org.mahesh.ems.mapper.PayrollMapper;
import org.mahesh.ems.repository.EmployeeRepository;
import org.mahesh.ems.repository.PayrollRepository;
import org.mahesh.ems.service.PayrollService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PayrollServiceImpl implements PayrollService {

    private final PayrollRepository payrollRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public PayrollResponse generatePayroll(PayrollRequest request) {

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        if (payrollRepository.existsByEmployeeIdAndMonth(
                request.getEmployeeId(),
                request.getMonth())) {

            throw new RuntimeException("Payroll already generated for this month");
        }

        Payroll payroll = new Payroll();

        payroll.setEmployee(employee);

        payroll.setMonth(request.getMonth());

        payroll.setBasicSalary(employee.getSalary());

        payroll.setBonus(request.getBonus());

        payroll.setDeduction(request.getDeduction());

        payroll.setNetSalary(
                employee.getSalary()
                        + request.getBonus()
                        - request.getDeduction());

        payroll.setPaymentDate(LocalDate.now());

        Payroll saved = payrollRepository.save(payroll);

        return PayrollMapper.toResponse(saved);
    }

    @Override
    public List<PayrollResponse> getAllPayrolls() {

        return payrollRepository.findAll()
                .stream()
                .map(PayrollMapper::toResponse)
                .toList();
    }

    @Override
    public PayrollResponse getPayrollById(Long id) {

        Payroll payroll = payrollRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payroll not found"));

        return PayrollMapper.toResponse(payroll);
    }
    @Override
    public List<PayrollResponse> getMyPayroll(String email) {

        return payrollRepository.findByEmployeeUserEmail(email)
                .stream()
                .map(PayrollMapper::toResponse)
                .toList();
    }
    @Override
    public void downloadSalarySlip(Long id,
                                   HttpServletResponse response)
            throws IOException {

        Payroll payroll = payrollRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payroll not found"));

        Document document = new Document(PageSize.A4);

        try {

            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            Font titleFont = FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD,
                    20,
                    Color.BLUE
            );

            Font headingFont = FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD,
                    14
            );

            Font normalFont = FontFactory.getFont(
                    FontFactory.HELVETICA,
                    12
            );

            Paragraph title = new Paragraph(
                    "EMPLOYEE SALARY SLIP",
                    titleFont
            );

            title.setAlignment(Element.ALIGN_CENTER);

            document.add(title);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Employee : "
                    + payroll.getEmployee().getFirstName()
                    + " "
                    + payroll.getEmployee().getLastName(),
                    headingFont));

            document.add(new Paragraph(
                    "Month : " + payroll.getMonth(),
                    normalFont));

            document.add(new Paragraph(
                    "Payment Date : " + payroll.getPaymentDate(),
                    normalFont));

            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(2);

            table.setWidthPercentage(100);

            table.setWidths(new float[]{3,2});

            table.addCell("Basic Salary");
            table.addCell(String.valueOf(payroll.getBasicSalary()));

            table.addCell("Bonus");
            table.addCell(String.valueOf(payroll.getBonus()));

            table.addCell("Deduction");
            table.addCell(String.valueOf(payroll.getDeduction()));

            table.addCell("Net Salary");
            table.addCell(String.valueOf(payroll.getNetSalary()));

            document.add(table);

            document.add(new Paragraph(" "));

            Paragraph footer = new Paragraph(
                    "Employee Management System",
                    headingFont
            );

            footer.setAlignment(Element.ALIGN_CENTER);

            document.add(footer);

        } catch (DocumentException e) {

            throw new IOException(e);

        } finally {

            document.close();

        }
    }
    @Override
    public void deletePayroll(Long id) {

        Payroll payroll = payrollRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payroll not found"));

        payrollRepository.delete(payroll);
    }

}
