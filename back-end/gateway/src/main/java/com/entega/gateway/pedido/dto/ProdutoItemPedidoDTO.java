package com.entega.gateway.pedido.dto;

import java.math.BigDecimal;

public record ProdutoItemPedidoDTO (
     Long idProduto,
     Integer quantidade,
     BigDecimal valorUnitario,
     BigDecimal desconto
){
}