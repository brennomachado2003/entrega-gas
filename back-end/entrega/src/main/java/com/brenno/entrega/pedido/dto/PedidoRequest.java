package com.brenno.entrega.pedido.dto;

import com.brenno.entrega.pedido.itemPedido.dto.ItemPedidoRequest;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

public record PedidoRequest (
        Integer usuarioId,
        Integer empresaId,
        Integer enderecoId,
        BigDecimal valorCompra,
        List<ItemPedidoRequest> produtos
){}
