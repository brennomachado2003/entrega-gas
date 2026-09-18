package com.entega.gateway.notificacao.controller;


import com.entega.gateway.notificacao.api.NotificacaoAPI;
import com.entega.gateway.notificacao.dto.SolicitacaoEntregaResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitacoes")
public class NotificacaoController {

    private final NotificacaoAPI notificacaoAPI;

    public NotificacaoController(NotificacaoAPI notificacaoAPI) {
        this.notificacaoAPI = notificacaoAPI;
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<SolicitacaoEntregaResponseDTO>> listarSolicitacoesPedido(@PathVariable Long pedidoId) {
        return notificacaoAPI.listarSolicitacoesPedido(pedidoId);
    }

    @GetMapping("/notificar/entregador/{idEntregador}")
    public ResponseEntity<List<SolicitacaoEntregaResponseDTO>> notificarEntregador(@PathVariable Long idEntregador) {
        return notificacaoAPI.notificarEntregador(idEntregador);
    }

    @PostMapping("/{idSolicitacao}/expirar")
    public ResponseEntity<String> expirar(@PathVariable Long idSolicitacao) {
        return notificacaoAPI.expirar(idSolicitacao);
    }

    @PostMapping("/{idSolicitacao}/recusar")
    public ResponseEntity<String> recusar(@PathVariable Long idSolicitacao) {
        return notificacaoAPI.recusar(idSolicitacao);
    }

    @PostMapping("/{id}/aceitar")
    public ResponseEntity<String> aceitar(@PathVariable Long id) {
        return notificacaoAPI.aceitar(id);
    }

    @PostMapping("/{pedidoId}/solicitarEntregador")
    public ResponseEntity<String> solicitarEntregador(@PathVariable Long pedidoId) {
        return notificacaoAPI.solicitarEntregador(pedidoId);
    }
}
