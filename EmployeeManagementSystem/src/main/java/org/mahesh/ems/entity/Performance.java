package org.mahesh.ems.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "performance")
@Data
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private Integer rating; // 1 to 5

    @Column(nullable = false, length = 1000)
    private String remarks;

    @Column(nullable = false)
    private LocalDate reviewDate;
}