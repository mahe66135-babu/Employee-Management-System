package org.mahesh.ems.service;


import org.mahesh.ems.dto.EmployeeRequest;
import org.mahesh.ems.dto.EmployeeResponse;
import org.mahesh.ems.dto.EmployeeStatisticsResponse;
import org.mahesh.ems.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface EmployeeService {
    EmployeeResponse saveEmployee(EmployeeRequest request);

    Page<EmployeeResponse> getAllEmployees(Pageable pageable);

//    List<EmployeeResponse> getAllEmployees();
    List<EmployeeResponse> getAllEmployees(String sortBy);

    EmployeeResponse getEmployeeById(Long id);

    EmployeeResponse updateEmployee(Long id, EmployeeRequest request);

    EmployeeResponse getMyProfile(String email);

    List<EmployeeResponse> searchByFirstName(String firstName);
//    List<EmployeeStatisticsResponse> getEmployeeStatistics();
    EmployeeStatisticsResponse getStatistics();
    List<EmployeeResponse> getInactiveEmployees();
    List<EmployeeResponse> getRecentEmployees();

    void deleteEmployee(Long id);

}
