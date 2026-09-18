package org.questao.entregador.entregador.service;

import org.questao.entregador.entregador.dominio.Entregador;
import org.questao.entregador.entregador.dominio.Senha;
import org.questao.entregador.entregador.infraestrutura.EntregadorRepository;
import org.questao.entregador.publicador.DomainEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CadastrarEntregadorService {

    private final EntregadorRepository entregadorRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final DomainEventPublisher domainEventPublisher;


    public CadastrarEntregadorService(EntregadorRepository entregadorRepository,
                                      BCryptPasswordEncoder passwordEncoder,
                                      DomainEventPublisher domainEventPublisher) {
        this.entregadorRepository = entregadorRepository;
        this.passwordEncoder = passwordEncoder;
        this.domainEventPublisher = domainEventPublisher;

    }

    public Entregador cadastrar(Entregador entregador) {
        Senha senha = new Senha(passwordEncoder.encode(entregador.getSenha().senha()));
        entregador.setSenha(senha);
        entregador.setCriadoEm(LocalDateTime.now());
        Entregador salvo = entregadorRepository.salvar(entregador);
        salvo.entregadorCadastrado();
        domainEventPublisher.publicar(salvo.getEvents());
        salvo.limparEvents();
        return salvo;
    }
}
