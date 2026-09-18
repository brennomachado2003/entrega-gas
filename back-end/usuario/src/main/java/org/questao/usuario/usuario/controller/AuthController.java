package org.questao.usuario.usuario.controller;


import org.questao.usuario.usuario.dto.LoginRequest;
import org.questao.usuario.usuario.dto.LoginResponse;
import org.questao.usuario.usuario.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse loginResponse = authService.login(request.getLogin(), request.getSenha());
        return ResponseEntity.ok(loginResponse);
    }
}
