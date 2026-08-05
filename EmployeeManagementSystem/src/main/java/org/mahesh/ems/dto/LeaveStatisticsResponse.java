package org.mahesh.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveStatisticsResponse {

    private long totalLeaves;
    private long pendingLeaves;
    private long approvedLeaves;
    private long rejectedLeaves;
}