package com.entega.gateway.pedido.dto;


import java.math.BigDecimal;


public record ItemPedidoRequest (
         Long produtoId,
         Integer quantidade,
         BigDecimal preco,
         BigDecimal desconto
){
}
