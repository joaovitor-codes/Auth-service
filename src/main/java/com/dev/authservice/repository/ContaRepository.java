package com.dev.authservice.repository;

import com.dev.authservice.model.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface ContaRepository extends JpaRepository<ContaEntity, UUID> {
    UserDetails findByEmail(String email);
    boolean existsByEmail(String email);
}
