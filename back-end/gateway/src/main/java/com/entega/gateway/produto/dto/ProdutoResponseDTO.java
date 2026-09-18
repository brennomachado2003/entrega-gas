package com.entega.gateway.produto.dto;


import java.math.BigDecimal;


public record ProdutoResponseDTO (
        Long idProduto,
        String nome,
        BigDecimal valor
){
    public ProdutoResponseDTO(Long idProduto, String nome, BigDecimal valor){
        this.idProduto = idProduto;
        this.nome = nome;
        this.valor = valor;
    }
}