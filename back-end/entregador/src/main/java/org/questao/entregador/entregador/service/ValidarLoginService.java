package org.questao.entregador.entregador.service;

import org.questao.entregador.entregador.dominio.Entregador;
import org.questao.entregador.entregador.infraestrutura.EntregadorRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ValidarLoginService {

    private final EntregadorRepository entregadorRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public ValidarLoginService(EntregadorRepository entregadorRepository, BCryptPasswordEncoder passwordEncoder) {
        this.entregadorRepository = entregadorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Entregador validarLogin(String cpf, String senha) {
        Entregador entregador = entregadorRepository.buscarPorCpf(cpf);
        if (entregador == null || !passwordEncoder.matches(senha, entregador.getSenha().senha())) throw new RuntimeException("Email ou senha inválidos");
        return entregador;
    }
}
