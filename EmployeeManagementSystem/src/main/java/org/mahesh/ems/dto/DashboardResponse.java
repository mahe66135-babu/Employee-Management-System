package org.mahesh.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponse {

    private long totalEmployees;
    private long totalDepartments;
    private long totalUsers;
    private long totalAdmins;
}