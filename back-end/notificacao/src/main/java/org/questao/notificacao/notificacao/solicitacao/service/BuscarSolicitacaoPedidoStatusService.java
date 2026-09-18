package org.questao.notificacao.notificacao.solicitacao.service;

import org.questao.notificacao.notificacao.solicitacao.dominio.SolicitacaoEntrega;
import org.questao.notificacao.notificacao.solicitacao.infraestrutura.SolicitarEntregaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarSolicitacaoPedidoStatusService {

    private final SolicitarEntregaRepository solicitacaoEntregaARepository;

    public BuscarSolicitacaoPedidoStatusService(SolicitarEntregaRepository solicitacaoEntregaARepository) {
        this.solicitacaoEntregaARepository = solicitacaoEntregaARepository;
    }

    public List<SolicitacaoEntrega> buscarListaSolicitacaoesStatus(Long idPedido) {
        return solicitacaoEntregaARepository.buscarListaSolicitacoesPedidoPendente(idPedido);
    }
}
