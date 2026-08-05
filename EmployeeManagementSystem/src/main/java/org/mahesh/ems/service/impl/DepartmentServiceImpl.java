package org.mahesh.ems.service.impl;

import org.mahesh.ems.dto.DepartmentRequest;
import org.mahesh.ems.dto.DepartmentResponse;
import org.mahesh.ems.entity.Department;
import org.mahesh.ems.exception.ResourceNotFoundException;
import org.mahesh.ems.mapper.DepartmentMapper;
import org.mahesh.ems.repository.DepartmentRepository;
import org.mahesh.ems.service.DepartmentService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentResponse saveDepartment(DepartmentRequest request) {

        Department department = new Department();

        department.setDepartmentName(request.getDepartmentName());
        department.setLocation(request.getLocation());

        Department savedDepartment = departmentRepository.save(department);

        return DepartmentMapper.toResponse(savedDepartment);
    }

    @Override
    public List<DepartmentResponse> getAllDepartments(Sort sort) {

        return departmentRepository.findAll(sort)
                .stream()
                .map(DepartmentMapper::toResponse)
                .toList();
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found with id : " + id));

        return DepartmentMapper.toResponse(department);
    }

    @Override
    public DepartmentResponse updateDepartment(Long id,
                                               DepartmentRequest request) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found with id : " + id));

        department.setDepartmentName(request.getDepartmentName());
        department.setLocation(request.getLocation());

        Department updatedDepartment = departmentRepository.save(department);

        return DepartmentMapper.toResponse(updatedDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found with id : " + id));

        departmentRepository.delete(department);
    }
}