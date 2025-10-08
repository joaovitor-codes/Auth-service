package com.dev.authservice.service;

import com.dev.authservice.dto.ContaRequest;
import com.dev.authservice.dto.ContaResponse;
import com.dev.authservice.dto.ContaUpdate;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ContaService {
    ContaResponse getContaById(UUID id);
    Page<ContaResponse> getContas(int page);
    void createConta(ContaRequest contaRequest);
    void deleteConta(UUID id);
    void updateConta(ContaUpdate contaUpdate, UUID id);
}
