package org.mahesh.ems.service.impl;

import org.mahesh.ems.dto.EmployeeStatisticsResponse;
import org.mahesh.ems.entity.User;

import org.mahesh.ems.dto.EmployeeRequest;
import org.mahesh.ems.dto.EmployeeResponse;
import org.mahesh.ems.entity.Department;
import org.mahesh.ems.entity.Employee;
import org.mahesh.ems.exception.ResourceNotFoundException;
import org.mahesh.ems.mapper.EmployeeMapper;
import org.mahesh.ems.repository.DepartmentRepository;
import org.mahesh.ems.repository.EmployeeRepository;
import org.mahesh.ems.repository.UserRepository;
import org.mahesh.ems.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository,UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
    }
    //paging
    @Override
    public Page<EmployeeResponse> getAllEmployees(Pageable pageable) {

        return employeeRepository.findByActiveTrue(pageable)
                .map(EmployeeMapper::toResponse);
    }
    //searching
    @Override
    public List<EmployeeResponse> searchByFirstName(String firstName) {

        return employeeRepository
                .findByFirstNameContainingIgnoreCase(firstName)
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();
    }
    //Logging
    private static final Logger logger =
            LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    public EmployeeResponse saveEmployee(EmployeeRequest request) {

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Employee employee = new Employee();

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setPhone(request.getPhone());
        employee.setSalary(request.getSalary());
        employee.setDesignation(request.getDesignation());
        employee.setJoiningDate(request.getJoiningDate());
        employee.setDepartment(department);
        employee.setUser(user);

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.toResponse(savedEmployee);
    }
    @Override
    public EmployeeResponse getMyProfile(String email) {

        Employee employee = employeeRepository.findByUserEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        return EmployeeMapper.toResponse(employee);
    }
    @Override
    public EmployeeResponse getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id : " + id));

        return EmployeeMapper.toResponse(employee);
    }
//
@Override
public EmployeeStatisticsResponse getStatistics() {

    return new EmployeeStatisticsResponse(

            employeeRepository.count(),

            employeeRepository.countByActiveTrue(),

            employeeRepository.countByActiveFalse(),

            employeeRepository.getAverageSalary(),

            employeeRepository.getHighestSalary(),

            employeeRepository.getLowestSalary()
    );
}
    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found"));

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setPhone(request.getPhone());
        employee.setSalary(request.getSalary());
        employee.setDesignation(request.getDesignation());
        employee.setJoiningDate(request.getJoiningDate());
        employee.setDepartment(department);
        

        employee.setActive(true);

        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.toResponse(updatedEmployee);
    }




//    @Override
//    public List<EmployeeResponse> getAllEmployees() {
//        return employeeRepository.findAll()
//                .stream()
//                .map(EmployeeMapper::toResponse)
//                .toList();
//    }
@Override
public List<EmployeeResponse> getAllEmployees(String sortBy) {

    return employeeRepository
            .findByActiveTrue()
            .stream()
            .sorted((e1, e2) -> {
                switch (sortBy) {
                    case "firstName":
                        return e1.getFirstName().compareToIgnoreCase(e2.getFirstName());
                    case "salary":
                        return Double.compare(e1.getSalary(), e2.getSalary());
                    default:
                        return Long.compare(e1.getId(), e2.getId());
                }
            })
            .map(EmployeeMapper::toResponse)
            .toList();
}
    @Override
    public List<EmployeeResponse> getInactiveEmployees() {

        return employeeRepository.findByActiveFalse()
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();
    }
    @Override
    public List<EmployeeResponse> getRecentEmployees() {

        return employeeRepository
                .findTop5ByOrderByIdDesc()
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        employee.setActive(false);

        employeeRepository.save(employee);
    }
}
