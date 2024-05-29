package com.sena.lunches.service.impl;

import com.sena.lunches.config.JwtService;
import com.sena.lunches.controller.models.AuthReponse;
import com.sena.lunches.controller.models.AuthenticateRequest;
import com.sena.lunches.controller.models.RegisterRequest;
import com.sena.lunches.entities.User_sena;
import com.sena.lunches.repository.User_sena_repo;
import com.sena.lunches.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceimpl implements AuthService {

    private final User_sena_repo userSenaRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthReponse register(RegisterRequest request) {

        var userSena = User_sena.builder()
                .id_user(request.getId_user())
                .document(request.getDocument())
                .type_document(request.getType_document())
                .nameUser(request.getNameUser())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .telephone(request.getTelephone())
                .keyword(passwordEncoder.encode(request.getKeyword()))
                .state(request.getState())
                .roles(request.getRoles())
                .build();
        userSenaRepo.save(userSena);
        var jwtToken = jwtService.generateToken(userSena);
        return AuthReponse.builder()
                .token(jwtToken).build();
    }

    @Override
    public AuthReponse authenticate(AuthenticateRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getKeyword()
                )
        );
        var userSena = userSenaRepo.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(userSena);
        return AuthReponse.builder().token(jwtToken).build();
    }
}
