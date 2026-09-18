package org.questao.pedidos.itemPedido.dto;


import java.math.BigDecimal;


public record ItemPedidoResponse(
         Long produtoId,
         Integer quantidade,
         BigDecimal preco,
         BigDecimal desconto
){
}
