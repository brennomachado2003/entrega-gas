package com.produtos.produtos.produto.dominio;


import java.math.BigDecimal;

public record Valor(BigDecimal valor) {

    public Valor {
        if (valor == null) throw new IllegalArgumentException("Valor não pode ser nulo");
        if (valor.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Valor deve ser maior que zero");

    }
}
