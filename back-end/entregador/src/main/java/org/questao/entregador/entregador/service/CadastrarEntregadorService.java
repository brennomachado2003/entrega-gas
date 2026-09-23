package org.questao.entregador.entregador.service;

import org.questao.entregador.Erros.ErroAoCadastrarEntregador;
import org.questao.entregador.entregador.dominio.Entregador;
import org.questao.entregador.entregador.dominio.Senha;
import org.questao.entregador.entregador.infraestrutura.EntregadorRepository;
import org.questao.entregador.publicador.DomainEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CadastrarEntregadorService {

    private final EntregadorRepository entregadorRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final DomainEventPublisher domainEventPublisher;
    private static final Logger log = LoggerFactory.getLogger(CadastrarEntregadorService.class);


    public CadastrarEntregadorService(EntregadorRepository entregadorRepository,
                                      BCryptPasswordEncoder passwordEncoder,
                                      DomainEventPublisher domainEventPublisher) {
        this.entregadorRepository = entregadorRepository;
        this.passwordEncoder = passwordEncoder;
        this.domainEventPublisher = domainEventPublisher;

    }

    public Entregador cadastrar(Entregador entregador) {
        try {
            Senha senha = new Senha(passwordEncoder.encode(entregador.getSenha().senha()));
            entregador.setSenha(senha);
            entregador.setCriadoEm(LocalDateTime.now());
            Entregador salvo = entregadorRepository.salvar(entregador);
            salvo.entregadorCadastrado();
            domainEventPublisher.publicar(salvo.getEvents());
            salvo.limparEvents();
            return salvo;
        }
        catch (Exception ex) {
            log.error("Erro ao cadastrar entregador: ", ex.getMessage());
            throw new ErroAoCadastrarEntregador(ex);
        }
    }
}
