package org.mahesh.ems.service.impl;

import lombok.RequiredArgsConstructor;
import org.mahesh.ems.dto.PerformanceRequest;
import org.mahesh.ems.dto.PerformanceResponse;
import org.mahesh.ems.entity.Employee;
import org.mahesh.ems.entity.Performance;
import org.mahesh.ems.exception.ResourceNotFoundException;
import org.mahesh.ems.mapper.PerformanceMapper;
import org.mahesh.ems.repository.EmployeeRepository;
import org.mahesh.ems.repository.PerformanceRepository;
import org.mahesh.ems.service.PerformanceService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceService {

    private final PerformanceRepository performanceRepository;
    private final EmployeeRepository employeeRepository;
//add reviews
    @Override
    public PerformanceResponse addReview(PerformanceRequest request) {

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        if (request.getRating() < 1 || request.getRating() > 5) {
            throw new RuntimeException("Rating must be between 1 and 5");
        }

        Performance performance = new Performance();

        performance.setEmployee(employee);
        performance.setRating(request.getRating());
        performance.setRemarks(request.getRemarks());
        performance.setReviewDate(LocalDate.now());

        Performance saved = performanceRepository.save(performance);

        return PerformanceMapper.toResponse(saved);
    }
    // Get All Reviews

    @Override
    public List<PerformanceResponse> getAllReviews() {

        return performanceRepository.findAll()
                .stream()
                .map(PerformanceMapper::toResponse)
                .toList();
    }
// Employee Reviews
    @Override
    public List<PerformanceResponse> getMyReviews(String email) {

        return performanceRepository.findByEmployeeUserEmail(email)
                .stream()
                .map(PerformanceMapper::toResponse)
                .toList();
    }
// Update Review
    @Override
    public PerformanceResponse updateReview(Long id,
                                            PerformanceRequest request) {

        Performance performance = performanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Review not found"));

        if (request.getRating() < 1 || request.getRating() > 5) {
            throw new RuntimeException("Rating must be between 1 and 5");
        }

        performance.setRating(request.getRating());
        performance.setRemarks(request.getRemarks());

        Performance updated = performanceRepository.save(performance);

        return PerformanceMapper.toResponse(updated);
    }
// Delete Review
    @Override
    public void deleteReview(Long id) {

        Performance performance = performanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Review not found"));

        performanceRepository.delete(performance);
    }



}