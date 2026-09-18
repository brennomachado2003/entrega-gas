package org.questao.usuario.usuario.service;

import org.questao.usuario.usuario.dominio.Usuario;
import org.questao.usuario.usuario.dto.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final ValidarLoginService validarLoginService;

    public AuthService(ValidarLoginService validarLoginService) {
        this.validarLoginService = validarLoginService;
    }

    public LoginResponse login(String login, String senha) {
        Usuario usuario = validarLoginService.validarLogin(login, senha);

        return new LoginResponse(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getEmail().email(),
                "USUARIO",
                usuario.isAtivo()
        );
    }
}