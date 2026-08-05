package org.mahesh.ems.repository;

import org.mahesh.ems.dto.EmployeeStatisticsResponse;
import org.mahesh.ems.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findByFirstNameContainingIgnoreCase(String firstName);
//    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByUserEmail(String email);
//    long count();
//    @Query("""
//SELECT new org.mahesh.ems.dto.EmployeeStatisticsResponse(
//d.departmentName,
//COUNT(e))
//FROM Employee e
//JOIN e.department d
//GROUP BY d.departmentName
//""")
//    List<EmployeeStatisticsResponse> getEmployeeStatistics();
    List<Employee> findByActiveTrue();
    Page<Employee> findByActiveTrue(Pageable pageable);
    List<Employee> findTop5ByOrderByIdDesc();

    List<Employee> findByActiveFalse();
    long countByActiveTrue();

    long countByActiveFalse();

    @Query("SELECT AVG(e.salary) FROM Employee e WHERE e.active=true")
    Double getAverageSalary();

    @Query("SELECT MAX(e.salary) FROM Employee e WHERE e.active=true")
    Double getHighestSalary();

    @Query("SELECT MIN(e.salary) FROM Employee e WHERE e.active=true")
    Double getLowestSalary();
}
