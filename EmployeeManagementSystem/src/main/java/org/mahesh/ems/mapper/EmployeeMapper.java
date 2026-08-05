package org.mahesh.ems.mapper;

import org.mahesh.ems.dto.EmployeeResponse;
import org.mahesh.ems.entity.Employee;

public class EmployeeMapper {

    public static EmployeeResponse toResponse(Employee employee) {

        EmployeeResponse response = new EmployeeResponse();
        response.setProfilePhoto(employee.getProfilePhoto());

        response.setId(employee.getId());
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
//        response.setEmail(employee.getEmail());
        if (employee.getUser() != null) {
            response.setEmail(employee.getUser().getEmail());
        }
        response.setPhone(employee.getPhone());
        response.setSalary(employee.getSalary());
        response.setDesignation(employee.getDesignation());
        response.setJoiningDate(employee.getJoiningDate());
        response.setDepartmentId(employee.getDepartment().getId());

        if (employee.getDepartment() != null) {
            response.setDepartmentName(
                    employee.getDepartment().getDepartmentName()
            );
        }

        return response;
    }
}