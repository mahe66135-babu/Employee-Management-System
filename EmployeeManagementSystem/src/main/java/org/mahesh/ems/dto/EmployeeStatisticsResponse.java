package org.mahesh.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeStatisticsResponse {

    private long totalEmployees;
    private long activeEmployees;
    private long inactiveEmployees;

    private Double averageSalary;
    private Double highestSalary;
    private Double lowestSalary;
}