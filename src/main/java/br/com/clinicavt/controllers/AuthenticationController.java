package br.com.clinicavt.controllers;

import br.com.clinicavt.infra.security.SecurityService;
import br.com.clinicavt.infra.security.TokenService;
import br.com.clinicavt.models.user.*;
import br.com.clinicavt.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    SecurityService securityService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/register/cliente")
    public ResponseEntity registerCliente(@RequestBody @Valid AuthenticationClienteDto authenticationClienteDto) {
        if (this.repository.findByUsername(authenticationClienteDto.username()) != null)
            return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(authenticationClienteDto.password());
        User newCLiente = new User(authenticationClienteDto.username(), encryptedPassword);
        newCLiente.setRoleCLiente();
        this.repository.save(newCLiente);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto authenticationDto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDto.username(), authenticationDto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword); // vai verificar se o usuario existe

        var token = tokenService.generateToken((User) auth.getPrincipal()); // vai gerar o token

        return ResponseEntity.ok(new LoginResponseDto(token)); // ou poderia retornar do jeito normal
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDto registerDto) {
        if (this.repository.findByUsername(registerDto.username()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());
        User newUser = new User(registerDto.username(), encryptedPassword, registerDto.role());
        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(securityService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(securityService.getById(id));
    }


}
