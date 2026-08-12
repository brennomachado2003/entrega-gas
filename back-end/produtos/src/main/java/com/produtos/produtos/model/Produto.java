package com.produtos.produtos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Integer idProduto;

    @Column(name = "nome")
    private String nome;

    @Column(name = "ncm")
    private String ncm;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "valor")
    private BigDecimal valor;
}
