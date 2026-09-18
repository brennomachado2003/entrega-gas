package org.questao.notificacao.notificacao.solicitacao.controller;

import org.questao.notificacao.notificacao.solicitacao.dominio.SolicitacaoEntrega;
import org.questao.notificacao.notificacao.solicitacao.dominio.StatusSolicitacao;
import org.questao.notificacao.notificacao.solicitacao.dto.SolicitacaoEntregaResponseDTO;
import org.questao.notificacao.notificacao.solicitacao.infraestrutura.SolicitacaoEntregaMapper;
import org.questao.notificacao.notificacao.solicitacao.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoEntregaController {

    private final BuscarSolicitacaoPedidoStatusService buscarSolicitacaoPedidoStatusService;
    private final NotificarEntregadoresService notificarEntregadoresService;
    private final AtualizarStatusSolicitacao atualizarStatusSolicitacao;
    private final CriarSolicitacoesService criarSolicitacoesService;
    private final SolicitacaoAceitaService solicitacaoAceitaService;

    public SolicitacaoEntregaController(
                                        BuscarSolicitacaoPedidoStatusService buscarSolicitacaoPedidoStatusService,
                                        NotificarEntregadoresService notificarEntregadoresService,
                                        AtualizarStatusSolicitacao atualizarStatusSolicitacao,
                                        CriarSolicitacoesService criarSolicitacoesService,
                                        SolicitacaoAceitaService solicitacaoAceitaService) {
        this.buscarSolicitacaoPedidoStatusService = buscarSolicitacaoPedidoStatusService;
        this.notificarEntregadoresService = notificarEntregadoresService;
        this.atualizarStatusSolicitacao = atualizarStatusSolicitacao;
        this.criarSolicitacoesService = criarSolicitacoesService;
        this.solicitacaoAceitaService = solicitacaoAceitaService;
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<SolicitacaoEntregaResponseDTO>> listarSolicitacoesPedido(@PathVariable Long pedidoId) {
        List<SolicitacaoEntrega> response = buscarSolicitacaoPedidoStatusService.buscarListaSolicitacaoesStatus(pedidoId);
        List<SolicitacaoEntregaResponseDTO> solicitacaoEntregaResponseDTOS = response.stream().map(SolicitacaoEntregaMapper::solicitacaoEntregaResponseDTO).toList();
        return ResponseEntity.ok(solicitacaoEntregaResponseDTOS);
    }

    @GetMapping("/notificar/entragador/{idEntregador}")
    public ResponseEntity<List<SolicitacaoEntregaResponseDTO>> notificarEntregador(@PathVariable Long idEntregador) {
        List<SolicitacaoEntrega> solicitacaoEntregas = notificarEntregadoresService.notificarEntregadores(idEntregador);
        List<SolicitacaoEntregaResponseDTO> solicitacaoEntregaResponseDTOS = solicitacaoEntregas.stream().map(SolicitacaoEntregaMapper::solicitacaoEntregaResponseDTO).toList();
        return ResponseEntity.ok(solicitacaoEntregaResponseDTOS);
    }

    @PostMapping("/{idSolicitacao}/expirar")
    public ResponseEntity<String> expirar(@PathVariable Long idSolicitacao) {
        atualizarStatusSolicitacao.atualizar(idSolicitacao, StatusSolicitacao.EXPIRADA);
        return ResponseEntity.ok("Solicitação expirada");
    }

    @PostMapping("/{idSolicitacao}/recusar")
    public ResponseEntity<String> recusar(@PathVariable Long idSolicitacao) {
        atualizarStatusSolicitacao.atualizar(idSolicitacao, StatusSolicitacao.RECUSADA);
        return ResponseEntity.ok("Solicitação recusada");
    }


    @PostMapping("/{id}/aceitar")
    public ResponseEntity<String> aceitar(@PathVariable Long id) {
        solicitacaoAceitaService.atualizar(id);
        return ResponseEntity.ok("Solicitacao aceitada");
    }


    @PostMapping("/{pedidoId}/solicitarEntregador")
    public ResponseEntity<String> solicitarEntregador(@PathVariable Long pedidoId) {
        List<SolicitacaoEntrega> solicitacaoEntregas = buscarSolicitacaoPedidoStatusService.buscarListaSolicitacaoesStatus(pedidoId);
        List<Long> entregadores = solicitacaoEntregas.stream().map(SolicitacaoEntrega::getEntregador).toList();
        criarSolicitacoesService.criarSolicitacoes(pedidoId, entregadores);
        return ResponseEntity.status(HttpStatus.CREATED).body("Solicitações enviadas com sucesso");
    }



}