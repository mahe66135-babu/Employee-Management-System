package org.mahesh.ems.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mahesh.ems.dto.EmployeeRequest;
import org.mahesh.ems.dto.EmployeeResponse;
import org.mahesh.ems.dto.EmployeeStatisticsResponse;
import org.mahesh.ems.service.EmployeeService;
import org.mahesh.ems.service.ProfilePhotoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employees")
@CrossOrigin("*")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ProfilePhotoService profilePhotoService;

    // No constructor here


    // ================= CREATE =================

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmployeeResponse> addEmployee(
            @Valid @RequestBody EmployeeRequest request) {

        EmployeeResponse response = employeeService.saveEmployee(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // ================= GET ALL (PAGING) =================

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<EmployeeResponse>> getAllEmployees(Pageable pageable) {

        return ResponseEntity.ok(employeeService.getAllEmployees(pageable));
    }

    // ================= SEARCH =================

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<List<EmployeeResponse>> searchEmployee(
            @RequestParam String firstName) {

        return ResponseEntity.ok(
                employeeService.searchByFirstName(firstName)
        );
    }
    @GetMapping("/sort")
    public ResponseEntity<List<EmployeeResponse>> sortEmployees(
            @RequestParam(defaultValue = "id") String sortBy) {

        return ResponseEntity.ok(
                employeeService.getAllEmployees(sortBy)
        );
    }

    // ================= MY PROFILE =================

    @GetMapping("/me")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<EmployeeResponse> myProfile(Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                employeeService.getMyProfile(email)
        );
    }
    @GetMapping("/inactive")
    @PreAuthorize("hasRole('ADMIN')")
    public List<EmployeeResponse> getInactiveEmployees() {

        return employeeService.getInactiveEmployees();
    }

    // ================= GET BY ID =================

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmployeeResponse> getEmployeeById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                employeeService.getEmployeeById(id)
        );
    }

    // ================= UPDATE =================

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request) {

        return ResponseEntity.ok(
                employeeService.updateEmployee(id, request)
        );
    }
    @PostMapping("/{id}/photo")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public String uploadPhoto(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {

        return profilePhotoService
                .uploadProfilePhoto(id, file);

    }

    // ================= DELETE =================

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.ok("Employee deleted successfully");
    }
//
@GetMapping("/statistics")
@PreAuthorize("hasRole('ADMIN')")
public EmployeeStatisticsResponse getStatistics() {

    return employeeService.getStatistics();
}
    @GetMapping("/recent")
//    @PreAuthorize("hasRole('ADMIN')")
    public List<EmployeeResponse> getRecentEmployees() {

        return employeeService.getRecentEmployees();
    }

    // ================= DEBUG =================

    @GetMapping("/debug")
    public String debug(Authentication authentication) {

        System.out.println("User: " + authentication.getName());
        System.out.println("Authorities: " + authentication.getAuthorities());

        return "OK";
    }
}
