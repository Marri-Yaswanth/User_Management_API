package com.example.demo.Repository;

import com.example.demo.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameContainingIgnoreCase(String name);
    List<User> findByEmailContainingIgnoreCase(String email);

    // Optional paginated methods
    Page<User> findAll(Pageable pageable);
    Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Optional<User> findByEmail(String email);
}
