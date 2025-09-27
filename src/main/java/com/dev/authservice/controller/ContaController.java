package com.dev.authservice.controller;

import com.dev.authservice.dto.ContaRequest;
import com.dev.authservice.dto.ContaResponse;
import com.dev.authservice.dto.ContaUpdate;
import com.dev.authservice.service.ContaService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/contas")
public class ContaController {
    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<Page<ContaResponse>> getAllContas(@PathVariable int page) {
        Page<ContaResponse> contaPage = contaService.getContas(page);
        return ResponseEntity.ok(contaPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaResponse> getContaById(@PathVariable UUID id) {
        return ResponseEntity.ok(contaService.getContaById(id));
    }

    @PostMapping
    public ResponseEntity<Void> criarConta(@RequestBody ContaRequest contaRequest) {
        contaService.createConta(contaRequest);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConta(@PathVariable UUID id) {
        contaService.deleteConta(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateConta(@PathVariable UUID id, @RequestBody ContaUpdate contaUpdate) {
        contaService.updateConta(contaUpdate, id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchConta(@PathVariable UUID id, @RequestBody ContaUpdate contaUpdate) {
        contaService.updateConta(contaUpdate, id);
        return ResponseEntity.noContent().build();
    }
}
