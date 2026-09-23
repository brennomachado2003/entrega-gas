package org.questao.entregador.entregador.service;

import org.questao.entregador.Erros.ErroAoValigarLoginEntregador;
import org.questao.entregador.entregador.dominio.Entregador;
import org.questao.entregador.entregador.infraestrutura.EntregadorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ValidarLoginService {

    private final EntregadorRepository entregadorRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final Logger log = LoggerFactory.getLogger(ValidarLoginService.class);

    public ValidarLoginService(EntregadorRepository entregadorRepository, BCryptPasswordEncoder passwordEncoder) {
        this.entregadorRepository = entregadorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Entregador validarLogin(String cpf, String senha) {
        try {
            Entregador entregador = entregadorRepository.buscarPorCpf(cpf);
            if (entregador == null || !passwordEncoder.matches(senha, entregador.getSenha().senha())) throw new RuntimeException("Email ou senha inválidos");
            return entregador;
        }
        catch (Exception ex) {
            log.error("Erro ao validar login entregador: ", ex.getMessage());
            throw new ErroAoValigarLoginEntregador(ex);
        }
    }
}
