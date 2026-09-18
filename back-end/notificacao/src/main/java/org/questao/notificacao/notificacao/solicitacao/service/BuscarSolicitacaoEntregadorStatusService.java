package org.questao.notificacao.notificacao.solicitacao.service;

import org.questao.notificacao.notificacao.solicitacao.dominio.SolicitacaoEntrega;
import org.questao.notificacao.notificacao.solicitacao.infraestrutura.SolicitarEntregaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarSolicitacaoEntregadorStatusService {

    private final SolicitarEntregaRepository solicitacaoEntregaARepository;

    public BuscarSolicitacaoEntregadorStatusService(SolicitarEntregaRepository solicitacaoEntregaARepository) {
        this.solicitacaoEntregaARepository = solicitacaoEntregaARepository;
    }

    public List<SolicitacaoEntrega> buscarListaSolicitacoesPendente(Long idEntrega) {
        return solicitacaoEntregaARepository.buscarListaSolicitacoesPendente(idEntrega);
    }
}
