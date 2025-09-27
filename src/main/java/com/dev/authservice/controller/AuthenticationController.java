package com.dev.authservice.controller;

import com.dev.authservice.dto.AuthenticationDto;
import com.dev.authservice.dto.RegisterDto;
import com.dev.authservice.model.ContaEntity;
import com.dev.authservice.repository.ContaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final ContaRepository contaRepository;

    public AuthenticationController(AuthenticationManager authenticationManager, ContaRepository contaRepository) {
        this.authenticationManager = authenticationManager;
        this.contaRepository = contaRepository;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto data){
        var usarNamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usarNamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto data){
        if (this.contaRepository.findByEmail(data.login()) != null){
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        ContaEntity conta = new ContaEntity(data.login(), encryptedPassword, data.role());
        this.contaRepository.save(conta);

        return ResponseEntity.ok().build();
    }
}
