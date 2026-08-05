package org.mahesh.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayrollResponse {

    private Long id;

    private String employeeName;

    private String month;

    private Double basicSalary;

    private Double bonus;

    private Double deduction;

    private Double netSalary;

    private LocalDate paymentDate;
}