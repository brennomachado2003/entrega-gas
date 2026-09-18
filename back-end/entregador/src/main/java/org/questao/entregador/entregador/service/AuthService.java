package org.questao.entregador.entregador.service;

import org.questao.entregador.entregador.dto.LoginResponse;
import org.questao.entregador.entregador.dominio.Entregador;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final ValidarLoginService validarLoginService;

    public AuthService(ValidarLoginService validarLoginService) {
        this.validarLoginService = validarLoginService;
    }

    public LoginResponse login(String login, String senha) {
        Entregador entregador = validarLoginService.validarLogin(login, senha);
        return new LoginResponse(
                entregador.getIdEntregador(),
                entregador.getNome(),
                entregador.getTelefone().telefone(),
                "Entregador",
                entregador.getAtivo().booleanValue()
        );
    }
}
