package com.esvelto.classroom.auth.repository;

import com.esvelto.classroom.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.modulith.NamedInterface;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@NamedInterface
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail (String email);
    boolean existsByEmail(String email);
}
