package org.questao.notificacao.notificacao.solicitacao.service;

import org.questao.notificacao.notificacao.solicitacao.dominio.StatusSolicitacao;
import org.questao.notificacao.notificacao.solicitacao.infraestrutura.SolicitarEntregaRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizarStatusSolicitacao {

    private final SolicitarEntregaRepository solicitacaoEntregaRepository;

    public AtualizarStatusSolicitacao(SolicitarEntregaRepository solicitacaoEntregaRepository) {
        this.solicitacaoEntregaRepository = solicitacaoEntregaRepository;
    }

    public void atualizar(Long idSolicitacao, StatusSolicitacao status) {
        solicitacaoEntregaRepository.atualizar(idSolicitacao, status);
    }
}
