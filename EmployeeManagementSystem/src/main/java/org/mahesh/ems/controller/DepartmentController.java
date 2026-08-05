package org.mahesh.ems.controller;

import jakarta.validation.Valid;
import org.mahesh.ems.dto.DepartmentRequest;
import org.mahesh.ems.dto.DepartmentResponse;
import org.mahesh.ems.service.DepartmentService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin("*")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // Create Department (ADMIN only)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DepartmentResponse> addDepartment(
            @Valid @RequestBody DepartmentRequest request) {

        DepartmentResponse response = departmentService.saveDepartment(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get All Departments (ADMIN & EMPLOYEE)
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public ResponseEntity<List<DepartmentResponse>> getAllDepartments(Sort sort) {

        return ResponseEntity.ok(departmentService.getAllDepartments(sort));
    }

    // Get Department By Id (ADMIN & EMPLOYEE)
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public ResponseEntity<DepartmentResponse> getDepartmentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                departmentService.getDepartmentById(id)
        );
    }

    // Update Department (ADMIN only)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DepartmentResponse> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentRequest request) {

        return ResponseEntity.ok(
                departmentService.updateDepartment(id, request)
        );
    }

    // Delete Department (ADMIN only)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteDepartment(
            @PathVariable Long id) {

        departmentService.deleteDepartment(id);

        return ResponseEntity.ok("Department deleted successfully");
    }
}