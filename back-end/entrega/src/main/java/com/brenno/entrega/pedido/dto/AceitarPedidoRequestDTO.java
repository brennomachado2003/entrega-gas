package com.brenno.entrega.pedido.dto;

import java.time.Instant;

public record AceitarPedidoRequestDTO (
        Integer pedidoId,
        Integer entregadorId
){
}
