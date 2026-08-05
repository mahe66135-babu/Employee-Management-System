package org.mahesh.ems.service.impl;
import lombok.RequiredArgsConstructor;
import org.mahesh.ems.entity.Employee;
import org.mahesh.ems.exception.ResourceNotFoundException;
import org.mahesh.ems.repository.EmployeeRepository;
import org.mahesh.ems.service.ProfilePhotoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class ProfilePhotoServiceImpl
        implements ProfilePhotoService {

    private final EmployeeRepository employeeRepository;

    private final String uploadDir =
            "uploads/profiles/";

    @Override
    public String uploadProfilePhoto(
            Long employeeId,
            MultipartFile file) {

        try {

            Employee employee = employeeRepository
                    .findById(employeeId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Employee not found"));

            String filename =
                    System.currentTimeMillis()
                            + "_"
                            + file.getOriginalFilename();

            Path path =
                    Paths.get(uploadDir + filename);

            Files.createDirectories(path.getParent());

            Files.copy(
                    file.getInputStream(),
                    path,
                    StandardCopyOption.REPLACE_EXISTING
            );

            employee.setProfilePhoto(filename);

            employeeRepository.save(employee);

            return filename;

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }

}