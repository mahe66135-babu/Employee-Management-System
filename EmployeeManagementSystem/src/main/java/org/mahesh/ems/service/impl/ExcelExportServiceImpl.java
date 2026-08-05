package org.mahesh.ems.service.impl;

import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mahesh.ems.entity.Employee;
import org.mahesh.ems.repository.EmployeeRepository;
import org.mahesh.ems.service.ExcelExportService;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExportServiceImpl implements ExcelExportService {

    private final EmployeeRepository employeeRepository;

    public ExcelExportServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void exportEmployees(HttpServletResponse response) throws IOException {

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Employees");

        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("First Name");
        header.createCell(2).setCellValue("Last Name");
        header.createCell(3).setCellValue("Email");
        header.createCell(4).setCellValue("Department");
        header.createCell(5).setCellValue("Designation");
        header.createCell(6).setCellValue("Salary");

        List<Employee> employees = employeeRepository.findAll();

        int rowCount = 1;

        for (Employee employee : employees) {

            Row row = sheet.createRow(rowCount++);

            row.createCell(0).setCellValue(employee.getId());
            row.createCell(1).setCellValue(employee.getFirstName());
            row.createCell(2).setCellValue(employee.getLastName());
            row.createCell(3).setCellValue(
                    employee.getUser() != null
                            ? employee.getUser().getEmail()
                            : ""
            );

            row.createCell(4).setCellValue(
                    employee.getDepartment().getDepartmentName());

            row.createCell(5).setCellValue(employee.getDesignation());

            row.createCell(6).setCellValue(employee.getSalary());

        }

        for (int i = 0; i < 7; i++) {

            sheet.autoSizeColumn(i);

        }

        workbook.write(response.getOutputStream());

        workbook.close();

    }
    @Override
    public void exportEmployeesPdf(HttpServletResponse response) throws IOException {

        Document document = new Document(PageSize.A4);

        try {

            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            Font titleFont = FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD,
                    18);

            Paragraph title =
                    new Paragraph("Employee Report", titleFont);

            title.setAlignment(Element.ALIGN_CENTER);

            document.add(title);

            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(6);

            table.setWidthPercentage(100);

            table.addCell("ID");
            table.addCell("First Name");
            table.addCell("Last Name");
            table.addCell("Email");
            table.addCell("Department");
            table.addCell("Designation");

            List<Employee> employees = employeeRepository.findAll();

            for (Employee employee : employees) {

                table.addCell(String.valueOf(employee.getId()));
                table.addCell(employee.getFirstName());
                table.addCell(employee.getLastName());

                table.addCell(
                        employee.getUser() != null
                                ? employee.getUser().getEmail()
                                : ""
                );

                table.addCell(
                        employee.getDepartment().getDepartmentName()
                );

                table.addCell(employee.getDesignation());

            }

            document.add(table);

        } catch (DocumentException e) {

            throw new IOException(e);

        } finally {

            document.close();

        }

    }
}