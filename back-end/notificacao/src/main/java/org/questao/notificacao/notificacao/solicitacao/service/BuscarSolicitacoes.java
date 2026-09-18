package org.questao.notificacao.notificacao.solicitacao.service;

import org.questao.notificacao.notificacao.solicitacao.dominio.SolicitacaoEntrega;
import org.questao.notificacao.notificacao.solicitacao.infraestrutura.SolicitarEntregaRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarSolicitacoes {

    private final SolicitarEntregaRepository solicitacaoEntregaRepository;

    public BuscarSolicitacoes(SolicitarEntregaRepository solicitacaoEntregaRepository) {
        this.solicitacaoEntregaRepository = solicitacaoEntregaRepository;
    }

    public SolicitacaoEntrega buscarSolicitacaoEntrega(Long idSolicitacao) {
        return solicitacaoEntregaRepository.buscar(idSolicitacao);
    }
}
