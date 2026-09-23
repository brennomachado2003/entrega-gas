package org.questao.estoque.movimentacaoTest;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.questao.estoque.estoque.movimentacao.dominio.Movimentacao;
import org.questao.estoque.estoque.movimentacao.dto.MovimentacaoRequestDTO;
import org.questao.estoque.estoque.movimentacao.infraestrutura.MovimentacaoRepository;
import org.questao.estoque.estoque.movimentacao.service.CargaInicialService;
import org.questao.estoque.estoque.movimentacao.service.ListarMovimentacoesService;
import org.questao.estoque.estoque.movimentacao.service.ReabastecerService;
import org.questao.estoque.estoque.movimentacao.service.VendaService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovimentacaoServiceTest {

    @Mock
    private MovimentacaoRepository movimentacaoRepository;

    @InjectMocks
    private CargaInicialService cargaInicialService;

    @InjectMocks
    private ReabastecerService reabastecerService;

    @InjectMocks
    private VendaService vendaService;

    @InjectMocks
    private ListarMovimentacoesService listarMovimentacoesService;


    @Test
    void deveRealizarCargaInicial() {

        MovimentacaoRequestDTO request = mock(MovimentacaoRequestDTO.class);
        Movimentacao movimentacao = mock(Movimentacao.class);

        when(request.quantidadeBotijaoCheio())
                .thenReturn(10);

        when(request.observacao())
                .thenReturn("Carga inicial");

        when(request.operacaoEntrega())
                .thenReturn(1L);

        when(movimentacaoRepository.salvar(org.mockito.ArgumentMatchers.any(Movimentacao.class)))
                .thenReturn(movimentacao);

        Movimentacao resultado =
                cargaInicialService.CargaInicial(request);

        assertNotNull(resultado);
        assertEquals(movimentacao, resultado);

        verify(movimentacaoRepository)
                .salvar(org.mockito.ArgumentMatchers.any(Movimentacao.class));
    }


    @Test
    void deveListarMovimentacoes() {

        List<Movimentacao> movimentacoes = List.of(
                mock(Movimentacao.class),
                mock(Movimentacao.class)
        );

        when(movimentacaoRepository.listar())
                .thenReturn(movimentacoes);

        List<Movimentacao> resultado =
                listarMovimentacoesService.listarMovimentacoes();

        assertNotNull(resultado);
        assertEquals(movimentacoes, resultado);

        verify(movimentacaoRepository).listar();
    }


    @Test
    void deveReabastecer() {

        MovimentacaoRequestDTO request = mock(MovimentacaoRequestDTO.class);
        Movimentacao movimentacao = mock(Movimentacao.class);

        when(request.quantidadeBotijaoCheio())
                .thenReturn(10);

        when(request.quantidadeBotijaoVazio())
                .thenReturn(5);

        when(request.operacaoEntrega())
                .thenReturn(1L);

        when(movimentacaoRepository.salvar(org.mockito.ArgumentMatchers.any(Movimentacao.class)))
                .thenReturn(movimentacao);

        Movimentacao resultado =
                reabastecerService.reabastecer(request);

        assertNotNull(resultado);
        assertEquals(movimentacao, resultado);

        verify(movimentacaoRepository)
                .salvar(org.mockito.ArgumentMatchers.any(Movimentacao.class));
    }


    @Test
    void deveRealizarVenda() {

        MovimentacaoRequestDTO request = mock(MovimentacaoRequestDTO.class);
        Movimentacao movimentacao = mock(Movimentacao.class);

        when(request.idPedido())
                .thenReturn(100L);

        when(request.quantidadeBotijaoCheio())
                .thenReturn(10);

        when(request.quantidadeBotijaoVazio())
                .thenReturn(5);

        when(request.quantidadeBotijaoCompleto())
                .thenReturn(8);

        when(request.observacao())
                .thenReturn("Venda realizada");

        when(request.operacaoEntrega())
                .thenReturn(1L);

        when(movimentacaoRepository.salvar(org.mockito.ArgumentMatchers.any(Movimentacao.class)))
                .thenReturn(movimentacao);

        Movimentacao resultado =
                vendaService.venda(request);

        assertNotNull(resultado);
        assertEquals(movimentacao, resultado);

        verify(movimentacaoRepository)
                .salvar(org.mockito.ArgumentMatchers.any(Movimentacao.class));
    }
}
