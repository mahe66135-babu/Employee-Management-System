package org.mahesh.ems.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(nullable = false)
    private String lastName;

//    @Email(message = "Invalid email")
//    @NotBlank(message = "Email is required")
//    @Column(nullable = false, unique = true)
//    private String email;

    @NotBlank(message = "Phone number is required")
    private String phone;

    @NotNull(message = "Salary is required")
    private Double salary;

    @NotBlank(message = "Designation is required")
    private String designation;

    @NotNull(message = "Joining date is required")
    private LocalDate joiningDate;



    @ManyToOne
    @JoinColumn(name = "department_id")
    @NotNull(message = "Department is required")
    private Department department;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(nullable = false)
    private Boolean active = true;

    @Column(name = "profile_photo")
    private String profilePhoto;

    // Constructors, Getters and Setters
}