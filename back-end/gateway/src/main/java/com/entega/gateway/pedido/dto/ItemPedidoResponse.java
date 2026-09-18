package com.entega.gateway.pedido.dto;


import java.math.BigDecimal;


public record ItemPedidoResponse(
         Long produtoId,
         Integer quantidade,
         BigDecimal preco,
         BigDecimal desconto
){
}
