package org.mahesh.ems.dto;

import lombok.Data;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;

@Data
public class LeaveRequestDto {

    private LocalDate startDate;

    private LocalDate endDate;

    @NotBlank(message = "Reason is required")
    private String reason;


}