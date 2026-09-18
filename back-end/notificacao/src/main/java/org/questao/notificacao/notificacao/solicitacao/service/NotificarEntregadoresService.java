package org.questao.notificacao.notificacao.solicitacao.service;

import org.questao.notificacao.notificacao.solicitacao.dominio.SolicitacaoEntrega;
import org.questao.notificacao.notificacao.solicitacao.infraestrutura.SolicitarEntregaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificarEntregadoresService {

    private final SolicitarEntregaRepository solicitacaoEntregaRepository;

    public NotificarEntregadoresService(SolicitarEntregaRepository solicitacaoEntregaRepository) {
        this.solicitacaoEntregaRepository = solicitacaoEntregaRepository;
    }

    public List<SolicitacaoEntrega> notificarEntregadores(Long idEntregador) {
        return solicitacaoEntregaRepository.buscarListaSolicitacoesPendente(idEntregador);
    }
}
