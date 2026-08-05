package org.mahesh.ems.service;

import org.mahesh.ems.dto.DepartmentRequest;
import org.mahesh.ems.dto.DepartmentResponse;
import org.mahesh.ems.entity.Department;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface DepartmentService {

    DepartmentResponse saveDepartment(DepartmentRequest request);

    List<DepartmentResponse> getAllDepartments(Sort sort);

    DepartmentResponse getDepartmentById(Long id);

    DepartmentResponse updateDepartment(Long id,
                                        DepartmentRequest request);

    void deleteDepartment(Long id);
}