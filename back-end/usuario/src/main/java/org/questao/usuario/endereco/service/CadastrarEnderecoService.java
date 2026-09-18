package org.questao.usuario.endereco.service;

import org.questao.usuario.endereco.dominio.Endereco;
import org.questao.usuario.endereco.infraestrutura.EnderecoRepository;
import org.questao.usuario.publicador.DomainEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CadastrarEnderecoService {

    private final EnderecoRepository enderecoRepositor;
    private final DomainEventPublisher domainEventPublisher;

    public CadastrarEnderecoService(EnderecoRepository enderecoRepositor,  DomainEventPublisher domainEventPublisher) {
        this.enderecoRepositor = enderecoRepositor;
        this.domainEventPublisher = domainEventPublisher;
    }

    public Endereco cadastrar(Endereco endereco){
        Endereco salvo = enderecoRepositor.salvar(endereco);
        salvo.enderecoCadastrado(salvo);
        domainEventPublisher.publicar(salvo.getEvents());
        salvo.limparEvents();
        return salvo;
    }
}
