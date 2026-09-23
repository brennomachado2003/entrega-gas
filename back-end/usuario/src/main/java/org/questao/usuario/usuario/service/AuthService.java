package org.questao.usuario.usuario.service;

import org.questao.usuario.Erros.endereco.ErroAoBuscarEnderecoPeloId;
import org.questao.usuario.Erros.usuario.ErroAoFazerLogin;
import org.questao.usuario.usuario.dominio.Usuario;
import org.questao.usuario.usuario.dto.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final ValidarLoginService validarLoginService;
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    public AuthService(ValidarLoginService validarLoginService) {
        this.validarLoginService = validarLoginService;
    }

    public LoginResponse login(String login, String senha) {
        try {
            Usuario usuario = validarLoginService.validarLogin(login, senha);
            return new LoginResponse(
                    usuario.getIdUsuario(),
                    usuario.getNome(),
                    usuario.getEmail().email(),
                    "USUARIO",
                    usuario.isAtivo()
            );
        }
        catch (Exception e) {
            log.error("Erro ao fazer o login ", e.getMessage());
            throw new ErroAoFazerLogin(e);
        }
    }
}