package com.dev.authservice.mapper;

import com.dev.authservice.dto.ContaRequest;
import com.dev.authservice.dto.ContaResponse;
import com.dev.authservice.model.ContaEntity;
import org.springframework.data.domain.Page;

public interface ContaMapper {
    ContaResponse toContaResponse(ContaEntity ContaEntity);
    ContaEntity toContaEntity(ContaRequest contaRequest);
    Page<ContaResponse> toContaResponsePage(Page<ContaEntity> contaEntities);
}
