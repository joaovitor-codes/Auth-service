package com.dev.authservice.mapper.impl;

import com.dev.authservice.dto.ContaRequest;
import com.dev.authservice.dto.ContaResponse;
import com.dev.authservice.mapper.ContaMapper;
import com.dev.authservice.model.ContaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContaMapperImpl implements ContaMapper {

    public ContaResponse toContaResponse(ContaEntity ContaEntity) {
        return new ContaResponse(
                ContaEntity.getId(),
                ContaEntity.getIdUsuario(),
                ContaEntity.getEmail()
        );
    }

    public ContaEntity toContaEntity(ContaRequest contaRequest) {
        return ContaEntity.builder()
                .email(contaRequest.email())
                .senha(contaRequest.senha())
                .build();
    }

    public Page<ContaResponse> toContaResponsePage(Page<ContaEntity> contaEntities) {
        if (contaEntities == null) {
            return null;
        }

        List<ContaResponse> contaResponses = contaEntities.getContent()
                .stream()
                .map(this::toContaResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(contaResponses, contaEntities.getPageable(), contaEntities.getTotalElements());
    }
}
