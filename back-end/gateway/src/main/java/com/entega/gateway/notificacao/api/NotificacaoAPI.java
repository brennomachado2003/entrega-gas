package com.entega.gateway.notificacao.api;



import com.entega.gateway.notificacao.dto.SolicitacaoEntregaResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@FeignClient(name = "notificacao", contextId = "notificacaoAPI")
public interface NotificacaoAPI {


    @GetMapping("/solicitacoes/pedido/{pedidoId}")
    ResponseEntity<List<SolicitacaoEntregaResponseDTO>> listarSolicitacoesPedido(@PathVariable Long pedidoId);

    @GetMapping("/solicitacoes/notificar/entragador/{idEntregador}")
    ResponseEntity<List<SolicitacaoEntregaResponseDTO>> notificarEntregador(@PathVariable Long idEntregador);

    @PostMapping("/solicitacoes/{id}/expirar")
    ResponseEntity<String> expirar(@PathVariable("id") Long idSolicitacao);

    @PostMapping("/solicitacoes/{id}/recusar")
    ResponseEntity<String> recusar(@PathVariable("id") Long idSolicitacao);


    @PostMapping("/solicitacoes/{id}/aceitar")
    ResponseEntity<String> aceitar(@PathVariable Long id);


    @PostMapping("/solicitacoes/{pedidoId}/solicitarEntregador")
    ResponseEntity<String> solicitarEntregador(@PathVariable Long pedidoId);


}
