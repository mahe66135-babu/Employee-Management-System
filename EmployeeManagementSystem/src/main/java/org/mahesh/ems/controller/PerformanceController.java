package org.mahesh.ems.controller;

import lombok.RequiredArgsConstructor;
import org.mahesh.ems.dto.PerformanceRequest;
import org.mahesh.ems.dto.PerformanceResponse;
import org.mahesh.ems.service.PerformanceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/performance")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PerformanceController {
    private final PerformanceService performanceService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public PerformanceResponse addReview(@RequestBody PerformanceRequest request){
        return performanceService.addReview(request);
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<PerformanceResponse>getAllReviews(){
        return performanceService.getAllReviews();
    }
    @GetMapping("/me")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public List<PerformanceResponse>getMyReviews(Authentication authentication){
        return performanceService.getMyReviews(authentication.getName());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public PerformanceResponse updateReview(
            @PathVariable Long id,
            @RequestBody PerformanceRequest request) {

        return performanceService.updateReview(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteReview(@PathVariable Long id) {

        performanceService.deleteReview(id);

        return "Review deleted successfully";
    }

}
