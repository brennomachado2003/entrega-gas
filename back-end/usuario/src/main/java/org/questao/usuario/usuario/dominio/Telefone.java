package org.questao.usuario.usuario.dominio;

public record Telefone (String telefone){
    public Telefone{
        if (telefone == null) throw new IllegalArgumentException("Telefone não pode ser nulo");
        telefone = telefone.replaceAll("\\D", "");
        if (telefone.length() != 11) throw new IllegalArgumentException("Telefone deve possuir 11 dígitos");
    }
}
