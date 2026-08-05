package org.mahesh.ems.mapper;

import org.mahesh.ems.dto.PerformanceResponse;
import org.mahesh.ems.entity.Performance;

public class PerformanceMapper {

    public static PerformanceResponse toResponse(Performance p){

        return new PerformanceResponse(
                p.getId(),
                p.getEmployee().getFirstName()+" "+p.getEmployee().getLastName(),
                p.getRating(),
                p.getRemarks(),
                p.getReviewDate()
        );
    }

}
