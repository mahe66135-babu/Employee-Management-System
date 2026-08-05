package org.mahesh.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mahesh.ems.entity.LeaveStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveResponse {

    private Long id;

    private String employeeName;

    private LocalDate startDate;

    private LocalDate endDate;

    private String reason;

    private LeaveStatus status;
}