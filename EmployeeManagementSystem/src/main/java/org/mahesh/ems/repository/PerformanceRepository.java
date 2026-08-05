package org.mahesh.ems.repository;

import org.mahesh.ems.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerformanceRepository
        extends JpaRepository<Performance, Long> {

    List<Performance> findByEmployeeId(Long employeeId);

    List<Performance> findByEmployeeUserEmail(String email);

}
