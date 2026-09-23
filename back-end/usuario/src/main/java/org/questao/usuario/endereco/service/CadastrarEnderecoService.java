package org.questao.usuario.endereco.service;

import org.questao.usuario.Erros.endereco.ErroAoCadastrarEndereco;
import org.questao.usuario.endereco.dominio.Endereco;
import org.questao.usuario.endereco.infraestrutura.EnderecoRepository;
import org.questao.usuario.publicador.DomainEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CadastrarEnderecoService {

    private static final Logger log = LoggerFactory.getLogger(CadastrarEnderecoService.class);
    private final EnderecoRepository enderecoRepositor;
    private final DomainEventPublisher domainEventPublisher;

    public CadastrarEnderecoService(EnderecoRepository enderecoRepositor,  DomainEventPublisher domainEventPublisher) {
        this.enderecoRepositor = enderecoRepositor;
        this.domainEventPublisher = domainEventPublisher;
    }

    public Endereco cadastrar(Endereco endereco){
        try {
            Endereco salvo = enderecoRepositor.salvar(endereco);
            salvo.enderecoCadastrado(salvo);
            domainEventPublisher.publicar(salvo.getEvents());
            salvo.limparEvents();
            return salvo;
        }
        catch (Exception e) {
            log.error("Erro ao cadastrar endereco", e);
            throw new ErroAoCadastrarEndereco(e);
        }
    }
}
