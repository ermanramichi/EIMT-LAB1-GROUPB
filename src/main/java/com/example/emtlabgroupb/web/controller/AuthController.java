package com.example.emtlabgroupb.web.controller;

import com.example.emtlabgroupb.model.domain.Role;
import com.example.emtlabgroupb.model.domain.User;
import com.example.emtlabgroupb.model.dto.JwtResponseDto;
import com.example.emtlabgroupb.model.dto.LoginRequestDto;
import com.example.emtlabgroupb.model.dto.RegisterRequestDto;
import com.example.emtlabgroupb.repository.UserRepository;
import com.example.emtlabgroupb.security.JwtHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Endpoints for authentication and registration")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;

    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtHelper jwtHelper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtHelper = jwtHelper;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user. Accepts an optional 'role' field (USER or ADMINISTRATOR).")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDto dto) {
        if (userRepository.existsByUsername(dto.username())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        Role role = Role.fromAny(dto.role());

        User user = new User(
                dto.username(),
                passwordEncoder.encode(dto.password()),
                dto.name(),
                role
        );
        userRepository.save(user);

        String token = jwtHelper.generateToken(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new JwtResponseDto(token, user.getUsername(), user.getName(), user.getRole().displayName()));
    }

    @PostMapping("/login")
    @Operation(summary = "Login and receive JWT token")
    public ResponseEntity<JwtResponseDto> login(@Valid @RequestBody LoginRequestDto dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.username(), dto.password())
        );

        User user = userRepository.findByUsername(dto.username()).orElseThrow();
        String token = jwtHelper.generateToken(user);

        return ResponseEntity.ok(new JwtResponseDto(token, user.getUsername(), user.getName(), user.getRole().displayName()));
    }
}
