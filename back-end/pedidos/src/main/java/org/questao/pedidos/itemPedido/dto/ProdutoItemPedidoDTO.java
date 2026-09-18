package org.questao.pedidos.itemPedido.dto;

import java.math.BigDecimal;

public record ProdutoItemPedidoDTO (
     Long idProduto,
     Integer quantidade,
     BigDecimal valorUnitario,
     BigDecimal desconto
){
}