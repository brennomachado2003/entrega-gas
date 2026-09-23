package org.questao.entregador.entregador.service;

import org.questao.entregador.Erros.ErroAoFazerLoginEntregador;
import org.questao.entregador.entregador.dto.LoginResponse;
import org.questao.entregador.entregador.dominio.Entregador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final ValidarLoginService validarLoginService;

    public AuthService(ValidarLoginService validarLoginService) {
        this.validarLoginService = validarLoginService;
    }

    public LoginResponse login(String login, String senha) {

        try {
            Entregador entregador = validarLoginService.validarLogin(login, senha);
            return new LoginResponse(
                    entregador.getIdEntregador(),
                    entregador.getNome(),
                    entregador.getTelefone().telefone(),
                    "Entregador",
                    entregador.getAtivo().booleanValue()
            );
        }
        catch (Exception ex) {
            log.error("Erro ao tentar fazer login entregador: ", ex.getMessage());
            throw new ErroAoFazerLoginEntregador(ex);
        }
    }
}
