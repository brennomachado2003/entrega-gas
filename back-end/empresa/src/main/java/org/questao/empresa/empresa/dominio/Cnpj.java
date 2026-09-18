package org.questao.empresa.empresa.dominio;

public record Cnpj(String cnpj) {

    public Cnpj {
        if (cnpj == null) throw new IllegalArgumentException("CNPJ não pode ser nulo");
        cnpj = cnpj.replaceAll("\\D", "");
        if (cnpj.length() != 14) throw new IllegalArgumentException("CNPJ deve possuir 14 dígitos");
    }
}
