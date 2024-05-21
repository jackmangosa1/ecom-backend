package com.auca.Shopy.repository;

import com.auca.Shopy.enums.UserRole;
import com.auca.Shopy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByEmail(String email);
    Optional<User> findFirstByUsername(String username);

    User findByRole(UserRole role);
}
