package org.mahesh.ems.repository;

import org.mahesh.ems.entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    List<Payroll> findByEmployeeId(Long employeeId);

    List<Payroll> findByEmployeeUserEmail(String email);

    boolean existsByEmployeeIdAndMonth(Long employeeId,
                                       String month);
}