package org.questao.notificacao;

import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.extension.ExtendWith;
import org.questao.notificacao.notificacao.solicitacao.dominio.SolicitacaoEntrega;
import org.questao.notificacao.notificacao.solicitacao.dominio.StatusSolicitacao;
import org.questao.notificacao.notificacao.solicitacao.infraestrutura.SolicitarEntregaRepository;
import org.questao.notificacao.notificacao.solicitacao.service.*;
import org.questao.notificacao.publicador.DomainEventPublisher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SolicitacaoServiceTest {

    @Mock
    private SolicitarEntregaRepository solicitacaoEntregaRepository;

    @Mock
    private DomainEventPublisher domainEventPublisherSSE;

    @Mock
    private DomainEventPublisher domainEventPublisherKafka;


    @InjectMocks
    private SolicitacaoAceitaService solicitacaoAceitaService;

    @InjectMocks
    private NotificarEntregadoresService notificarEntregadoresService;

    @InjectMocks
    private CriarSolicitacoesService criarSolicitacoesService;

    @InjectMocks
    private BuscarSolicitacoes buscarSolicitacoes;

    @InjectMocks
    private BuscarSolicitacaoPedidoStatusService buscarSolicitacaoPedidoStatusService;

    @InjectMocks
    private BuscarSolicitacaoEntregadorStatusService buscarSolicitacaoEntregadorStatusService;

    @InjectMocks
    private AtualizarStatusSolicitacao atualizarStatusSolicitacao;


    @Test
    void deveNotificarEntregadores() {

        Long idEntregador = 1L;

        List<SolicitacaoEntrega> solicitacoes = List.of(
                mock(SolicitacaoEntrega.class),
                mock(SolicitacaoEntrega.class)
        );

        when(solicitacaoEntregaRepository
                .buscarListaSolicitacoesPendente(idEntregador))
                .thenReturn(solicitacoes);

        List<SolicitacaoEntrega> resultado =
                notificarEntregadoresService
                        .notificarEntregadores(idEntregador);

        assertSame(solicitacoes, resultado);

        verify(solicitacaoEntregaRepository)
                .buscarListaSolicitacoesPendente(idEntregador);
    }


    @Test
    void deveBuscarSolicitacao() {

        Long idSolicitacao = 1L;

        SolicitacaoEntrega solicitacao =
                mock(SolicitacaoEntrega.class);

        when(solicitacaoEntregaRepository.buscar(idSolicitacao))
                .thenReturn(solicitacao);

        SolicitacaoEntrega resultado =
                buscarSolicitacoes
                        .buscarSolicitacaoEntrega(idSolicitacao);

        assertSame(solicitacao, resultado);

        verify(solicitacaoEntregaRepository)
                .buscar(idSolicitacao);
    }


    @Test
    void deveBuscarSolicitacoesPorPedido() {

        Long idPedido = 1L;

        List<SolicitacaoEntrega> solicitacoes = List.of(
                mock(SolicitacaoEntrega.class),
                mock(SolicitacaoEntrega.class)
        );

        when(solicitacaoEntregaRepository
                .buscarListaSolicitacoesPedidoPendente(idPedido))
                .thenReturn(solicitacoes);

        List<SolicitacaoEntrega> resultado =
                buscarSolicitacaoPedidoStatusService
                        .buscarListaSolicitacaoesStatus(idPedido);

        assertSame(solicitacoes, resultado);

        verify(solicitacaoEntregaRepository)
                .buscarListaSolicitacoesPedidoPendente(idPedido);
    }


    @Test
    void deveBuscarSolicitacoesPorEntregador() {

        Long idEntregador = 1L;

        List<SolicitacaoEntrega> solicitacoes = List.of(
                mock(SolicitacaoEntrega.class),
                mock(SolicitacaoEntrega.class)
        );

        when(solicitacaoEntregaRepository
                .buscarListaSolicitacoesPendente(idEntregador))
                .thenReturn(solicitacoes);

        List<SolicitacaoEntrega> resultado =
                buscarSolicitacaoEntregadorStatusService
                        .buscarListaSolicitacoesPendente(idEntregador);

        assertSame(solicitacoes, resultado);

        verify(solicitacaoEntregaRepository)
                .buscarListaSolicitacoesPendente(idEntregador);
    }


    @Test
    void deveAtualizarStatusDaSolicitacao() {

        Long idSolicitacao = 1L;
        StatusSolicitacao status = StatusSolicitacao.ACEITA;

        atualizarStatusSolicitacao
                .atualizar(idSolicitacao, status);

        verify(solicitacaoEntregaRepository)
                .atualizar(idSolicitacao, status);
    }

    @Test
    void deveCriarSolicitacoes() {

        Long pedido = 1L;

        List<Long> entregadores = List.of(
                10L,
                20L,
                30L
        );

        when(solicitacaoEntregaRepository.salvar(any(SolicitacaoEntrega.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        criarSolicitacoesService
                .criarSolicitacoes(pedido, entregadores);

        verify(solicitacaoEntregaRepository, times(3))
                .salvar(any(SolicitacaoEntrega.class));

    }
}
