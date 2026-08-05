package org.mahesh.ems.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class EmployeeResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private Double salary;

    private String designation;

    private LocalDate joiningDate;

    private Long departmentId;

    private String departmentName;

    private String profilePhoto;

    // Generate getters and setters
}