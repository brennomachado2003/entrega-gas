package org.questao.pedidos.pedido.dto;


import org.questao.pedidos.itemPedido.dto.ItemPedidoResponse;
import org.questao.pedidos.pedido.dominio.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public record PedidoEntregaResponseDTO (
    Long idPedido,
    Long idUsuario,
    Long idEndereco,
    BigDecimal valorCompra,
    List<ItemPedidoResponse> produtos,
    LocalDateTime dataPedido,
    StatusPedido status
){ }