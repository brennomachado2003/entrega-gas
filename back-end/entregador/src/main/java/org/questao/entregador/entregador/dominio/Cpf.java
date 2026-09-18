package org.questao.entregador.entregador.dominio;

public record Cpf(String cpf) {

    public Cpf {
        if (cpf == null) throw new IllegalArgumentException("CPF não pode ser nulo");
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11) throw new IllegalArgumentException("CPF deve possuir 11 dígitos");
    }
}
