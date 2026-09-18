package com.entega.gateway.pedido.controller;


import com.entega.gateway.pedido.api.PedidoAPI;
import com.entega.gateway.pedido.dto.AceitarPedidoRequestDTO;
import com.entega.gateway.pedido.dto.PedidoEntregaResponseDTO;
import com.entega.gateway.pedido.dto.PedidoRequest;
import com.entega.gateway.pedido.dto.PedidoResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private PedidoAPI pedidoAPI;

    public PedidoController(PedidoAPI pedidoAPI) {
        this.pedidoAPI = pedidoAPI;
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> cadastrar(@RequestBody PedidoRequest pedidoRequest){
        return pedidoAPI.cadastrar(pedidoRequest);
    }

    @GetMapping("/historico")
    public ResponseEntity<List<PedidoEntregaResponseDTO>> listaHistorico(@RequestParam("usuarioId") Long usuarioId){
        return pedidoAPI.listaHistorico(usuarioId);
    }

    @GetMapping("/historico/entrega")
    public ResponseEntity<List<PedidoEntregaResponseDTO>> listaEntrega(@RequestParam("entregadorId") Long entregadorId){
        return pedidoAPI.listaEntrega(entregadorId);
    }

    @PatchMapping("/cancelarPedido")
    public ResponseEntity<PedidoResponseDTO> cancelarPedido(@RequestBody Long pedidoId){
        return pedidoAPI.cancelarPedido(pedidoId);
    }

    @PatchMapping("/entregadorDefinido")
    public ResponseEntity<PedidoResponseDTO> entregadoDefinido(@RequestBody AceitarPedidoRequestDTO aceitarPedidoRequestDTO){
        return pedidoAPI.entregadoDefinido(aceitarPedidoRequestDTO);
    }


}
