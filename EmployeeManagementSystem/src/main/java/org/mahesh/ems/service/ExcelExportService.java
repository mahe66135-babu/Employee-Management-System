package org.mahesh.ems.service;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ExcelExportService {

    void exportEmployees(HttpServletResponse response) throws IOException;
    void exportEmployeesPdf(HttpServletResponse response) throws IOException;

}