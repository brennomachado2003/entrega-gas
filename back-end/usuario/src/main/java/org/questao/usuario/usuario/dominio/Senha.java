package org.questao.usuario.usuario.dominio;

public record Senha(String senha) {

    public Senha {
        if (senha == null || senha.isBlank()) throw new IllegalArgumentException("Senha não pode ser vazia");
    }
}
