package org.mahesh.ems.repository;

import org.mahesh.ems.entity.LeaveRequest;
import org.mahesh.ems.entity.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRepository
        extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByEmployeeId(Long employeeId);

    List<LeaveRequest> findByStatus(LeaveStatus status);
    boolean existsByEmployeeIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Long employeeId,
            LocalDate endDate,
            LocalDate startDate);
    long countByStatus(LeaveStatus status);
    
}
