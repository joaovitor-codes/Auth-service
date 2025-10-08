package com.dev.authservice.service.impl;

import com.dev.authservice.dto.ContaRequest;
import com.dev.authservice.dto.ContaResponse;
import com.dev.authservice.dto.ContaUpdate;
import com.dev.authservice.exceptions.BusinessRuleException;
import com.dev.authservice.exceptions.IllegalArgumentException;
import com.dev.authservice.exceptions.ResourceNotFoundException;
import com.dev.authservice.mapper.ContaMapper;
import com.dev.authservice.model.ContaEntity;
import com.dev.authservice.repository.ContaRepository;
import com.dev.authservice.service.ContaService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContaServiceImpl implements ContaService {
    private final ContaRepository contaRepository;
    private final ContaMapper contaMapper;

    public ContaServiceImpl(ContaRepository contaRepository, ContaMapper contaMapper) {
        this.contaRepository = contaRepository;
        this.contaMapper = contaMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public ContaResponse getContaById(UUID id) {
        var contaEntity = contaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada"));

        return contaMapper.toContaResponse(contaEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ContaResponse> getContas(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<ContaEntity> contaEntitiesPage = contaRepository.findAll(pageable);
        return contaMapper.toContaResponsePage(contaEntitiesPage);
    }

    @Override
    @Transactional
    public void createConta(ContaRequest contaRequest) {
        if (contaRequest == null) {
            throw new IllegalArgumentException("ContaRequest não pode ser nulo");
        }

        if (contaRepository.existsByEmail(contaRequest.email())) {
            throw new BusinessRuleException("Email já está em uso");
        }

        var contaEntity = contaMapper.toContaEntity(contaRequest);
        contaEntity.setSenha(contaRequest.senha());
        contaRepository.save(contaEntity);
    }

    @Override
    @Transactional
    public void deleteConta(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }

        var contaEntity = contaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada"));

        contaRepository.delete(contaEntity);
    }

    @Override
    @Transactional
    public void updateConta(ContaUpdate contaUpdate, UUID id) {
        var contaEntity = contaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada"));

        contaUpdate.email().ifPresent(contaEntity::setEmail);
        contaUpdate.senha().ifPresent(contaEntity::setSenha);
        contaRepository.save(contaEntity);
    }

}
