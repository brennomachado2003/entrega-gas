package org.questao.pedidos.pedido.dto;

public record AceitarPedidoRequestDTO (
        Long pedidoId,
        Long entregadorId
){
}
