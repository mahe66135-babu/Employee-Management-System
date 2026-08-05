package org.mahesh.ems.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentResponse {

    private Long id;
    private String departmentName;
    private String location;
}