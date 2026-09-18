package com.produtos.produtos.produto.dominio;


import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Produto {
    private final Long idProduto;
    private String nome;
    private NCM ncm;
    private int quantidade;
    private Valor valor;

    public  Produto(Long idProduto, String nome, String ncm, int quantidade, BigDecimal valor) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.ncm = new NCM(ncm);
        this.quantidade = quantidade;
        this.valor = new Valor(valor);
    }

    public static Produto reconstituir(Long idProduto, String nome, String ncm, int quantidade, BigDecimal valor) {
        return new Produto(idProduto, nome, ncm, quantidade, valor);
    }

    public void atualizar(Produto dados) {
        this.nome = dados.getNome();
        this.ncm = dados.getNcm();
        this.quantidade = dados.getQuantidade();
        this.valor = dados.getValor();
    }

}
