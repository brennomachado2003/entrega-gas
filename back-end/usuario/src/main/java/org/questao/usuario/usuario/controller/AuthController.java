package org.questao.usuario.usuario.controller;


import org.questao.usuario.Erros.usuario.ErroAoFazerLogin;
import org.questao.usuario.usuario.dto.LoginRequest;
import org.questao.usuario.usuario.dto.LoginResponse;
import org.questao.usuario.usuario.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @ExceptionHandler(ErroAoFazerLogin.class)
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        log.info("Requisicao fazer login recebido {}", request);
        LoginResponse loginResponse = authService.login(request.getLogin(), request.getSenha());
        return ResponseEntity.ok(loginResponse);
    }
}
