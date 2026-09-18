package com.entega.gateway.pedido.api;



import com.entega.gateway.pedido.dto.AceitarPedidoRequestDTO;
import com.entega.gateway.pedido.dto.PedidoEntregaResponseDTO;
import com.entega.gateway.pedido.dto.PedidoRequest;
import com.entega.gateway.pedido.dto.PedidoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "pedidos", contextId = "pedidoAPI")
public interface PedidoAPI {

    @PostMapping("/pedido")
    ResponseEntity<PedidoResponseDTO> cadastrar(@RequestBody PedidoRequest pedidoRequest);

    @GetMapping("/pedido/historico")
    ResponseEntity<List<PedidoEntregaResponseDTO>> listaHistorico(@RequestParam("usuarioId") Long usuarioId);

    @GetMapping("/pedido/historico/entrega")
    ResponseEntity<List<PedidoEntregaResponseDTO>> listaEntrega(@RequestParam("entregadorId") Long entregadorId);

    @PatchMapping("/pedido/cancelarPedido")
    ResponseEntity<PedidoResponseDTO> cancelarPedido(@RequestBody Long pedidoId);

    @PatchMapping("/pedido/entregadorDefinido")
    ResponseEntity<PedidoResponseDTO> entregadoDefinido(@RequestBody AceitarPedidoRequestDTO aceitarPedidoRequestDTO);

}
