package org.mahesh.ems.dto;

import lombok.Data;

@Data
public class PayrollRequest {

    private Long employeeId;

    private String month;

    private Double bonus;

    private Double deduction;
}