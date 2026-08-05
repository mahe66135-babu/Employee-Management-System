package org.mahesh.ems.repository;

import org.mahesh.ems.entity.Role;
import org.mahesh.ems.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
    long countByRole(Role role);
}
