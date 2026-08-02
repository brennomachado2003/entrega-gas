package com.brenno.entrega.pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

public record PedidoResponseDTO (
     Integer pedidoId,
     String mensagem
){}