package com.entega.gateway.pedido.dto;

public record AceitarPedidoRequestDTO (
        Long pedidoId,
        Long entregadorId
){
}
