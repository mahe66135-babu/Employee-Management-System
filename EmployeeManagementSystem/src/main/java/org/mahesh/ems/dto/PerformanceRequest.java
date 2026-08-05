package org.mahesh.ems.dto;

import lombok.Data;

@Data
public class PerformanceRequest {

    private Long employeeId;

    private Integer rating;

    private String remarks;
}