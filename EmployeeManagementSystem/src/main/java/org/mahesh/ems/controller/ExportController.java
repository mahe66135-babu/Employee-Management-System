package org.mahesh.ems.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.mahesh.ems.service.ExcelExportService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/export")
@CrossOrigin("*")
public class ExportController {

    private final ExcelExportService excelExportService;

    public ExportController(ExcelExportService excelExportService) {
        this.excelExportService = excelExportService;
    }

    @GetMapping("/employees/excel")
    @PreAuthorize("hasRole('ADMIN')")
    public void exportEmployees(
            HttpServletResponse response) throws IOException {

        response.setContentType(
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        response.setHeader(
                "Content-Disposition",
                "attachment; filename=employees.xlsx");

        excelExportService.exportEmployees(response);

    }
    @GetMapping("/employees/pdf")
    @PreAuthorize("hasRole('ADMIN')")
    public void exportEmployeesPdf(
            HttpServletResponse response)
            throws IOException {

        response.setContentType("application/pdf");

        response.setHeader(
                "Content-Disposition",
                "attachment; filename=employees.pdf");

        excelExportService.exportEmployeesPdf(response);

    }
}