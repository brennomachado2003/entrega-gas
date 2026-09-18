package org.questao.notificacao.notificacao.solicitacao.infraestrutura;

import org.questao.notificacao.notificacao.solicitacao.dominio.SolicitacaoEntrega;
import org.questao.notificacao.notificacao.solicitacao.dominio.StatusSolicitacao;

import java.util.List;
import java.util.stream.Collectors;

public interface SolicitarEntregaRepository {

     List<SolicitacaoEntrega> listar();
     SolicitacaoEntrega buscar(Long id);
     SolicitacaoEntrega salvar(SolicitacaoEntrega solicitacaoEntrega);
     SolicitacaoEntrega atualizar(Long id, StatusSolicitacao dados);
    List<SolicitacaoEntrega> buscarListaSolicitacoesPendente(Long idEntrega);
    List<SolicitacaoEntrega> buscarListaSolicitacoesPedidoPendente(Long idPedido);
    void solicitacoesRecusadas(Long idPedido);
}
