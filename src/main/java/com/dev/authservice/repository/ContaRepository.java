package com.dev.authservice.repository;

import com.dev.authservice.model.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface ContaRepository extends JpaRepository<ContaEntity, UUID> {
    UserDetails findByEmail(String email);
    boolean existsByEmail(String email);
}
