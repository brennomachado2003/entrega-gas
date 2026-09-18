package com.entega.gateway.pedido.dto;


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