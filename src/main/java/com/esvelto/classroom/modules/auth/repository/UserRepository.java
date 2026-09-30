package com.esvelto.classroom.modules.auth.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esvelto.classroom.modules.auth.models.User;

@Repository 
public interface UserRepository extends JpaRepository<UUID, User> {
    Optional<User> findByEmail(String email);
}
