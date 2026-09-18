package com.produtos.produtos.produto.dominio;

public record NCM(String ncm) {

    public NCM {
        if (ncm == null) throw new IllegalArgumentException("NCM não pode ser nulo");
        ncm = ncm.replaceAll("\\D", "");
        if (ncm.length() != 8) throw new IllegalArgumentException("NCM deve possuir 8 dígitos");
    }
}
