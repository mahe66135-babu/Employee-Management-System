package org.mahesh.ems.service.impl;

import org.mahesh.ems.dto.DashboardResponse;
import org.mahesh.ems.entity.Role;
import org.mahesh.ems.repository.DepartmentRepository;
import org.mahesh.ems.repository.EmployeeRepository;
import org.mahesh.ems.repository.UserRepository;
import org.mahesh.ems.service.DashboardService;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    public DashboardServiceImpl(
            EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository,
            UserRepository userRepository) {

        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
    }

    @Override
    
    public DashboardResponse getDashboard() {

        return new DashboardResponse(
                employeeRepository.count(),
                departmentRepository.count(),
                userRepository.count(),
                userRepository.countByRole(Role.ADMIN)
        );
    }
}