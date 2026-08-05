package org.mahesh.ems.service;

import org.mahesh.ems.dto.PerformanceRequest;
import org.mahesh.ems.dto.PerformanceResponse;

import java.util.List;

public interface PerformanceService {

    PerformanceResponse addReview(PerformanceRequest request);

    List<PerformanceResponse> getAllReviews();

    List<PerformanceResponse> getMyReviews(String email);

    PerformanceResponse updateReview(Long id,
                                     PerformanceRequest request);

    void deleteReview(Long id);
}