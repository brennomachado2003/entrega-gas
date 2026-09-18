package com.entega.gateway.pedido.dto;


import java.math.BigDecimal;
import java.util.List;

public record PedidoRequest (
        Long usuarioId,
        Long empresaId,
        Long enderecoId,
        BigDecimal valorCompra,
        List<ItemPedidoRequest> produtos
){}
