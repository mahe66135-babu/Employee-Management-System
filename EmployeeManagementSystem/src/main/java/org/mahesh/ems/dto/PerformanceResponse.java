package org.mahesh.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceResponse {

    private Long id;

    private String employeeName;

    private Integer rating;

    private String remarks;

    private LocalDate reviewDate;
}