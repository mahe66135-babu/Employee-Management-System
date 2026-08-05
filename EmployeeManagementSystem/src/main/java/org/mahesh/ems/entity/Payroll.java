package org.mahesh.ems.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "payroll")
@Data
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private String month;

    @Column(nullable = false)
    private Double basicSalary;

    @Column(nullable = false)
    private Double bonus;

    @Column(nullable = false)
    private Double deduction;

    @Column(nullable = false)
    private Double netSalary;

    @Column(nullable = false)
    private LocalDate paymentDate;
}