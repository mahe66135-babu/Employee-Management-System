package org.mahesh.ems.mapper;

import org.mahesh.ems.dto.DepartmentResponse;
import org.mahesh.ems.entity.Department;

public class DepartmentMapper {

    public static DepartmentResponse toResponse(Department department) {

        return DepartmentResponse.builder()
                .id(department.getId())
                .departmentName(department.getDepartmentName())
                .location(department.getLocation())
                .build();
    }
}