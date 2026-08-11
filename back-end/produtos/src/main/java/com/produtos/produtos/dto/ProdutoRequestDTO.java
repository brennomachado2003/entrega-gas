package com.brenno.entrega.produto.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoRequestDTO {

    private String nome;
    private String ncm;
    private Integer quantidade;
    private BigDecimal valor;
}