package com.entega.gateway.produto.dto;

import java.math.BigDecimal;

public record ProdutoRequestDTO (
     String nome,
     String ncm,
     Integer quantidade,
     BigDecimal valor
){
}