package org.mahesh.ems.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;


import java.time.LocalDate;
@Data
public class EmployeeRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank
    private String phone;

    @NotNull
    private Double salary;

    @NotBlank
    private String designation;

    @NotNull
    private LocalDate joiningDate;

    @NotNull
    private Long departmentId;

    // Generate getters and setters
}