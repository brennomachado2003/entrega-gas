package org.questao.pedidos.pedido.dto;

import org.questao.pedidos.itemPedido.dto.ItemPedidoRequest;

import java.math.BigDecimal;
import java.util.List;

public record PedidoRequest (
        Long usuarioId,
        Long empresaId,
        Long enderecoId,
        BigDecimal valorCompra,
        List<ItemPedidoRequest> produtos
){}
