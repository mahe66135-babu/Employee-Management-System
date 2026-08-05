package org.mahesh.ems.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ProfilePhotoService {

    String uploadProfilePhoto(Long employeeId,
                              MultipartFile file);

}