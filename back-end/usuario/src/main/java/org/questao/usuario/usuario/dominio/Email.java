package org.questao.usuario.usuario.dominio;


public record Email(String email) {

    public Email {
        if (email == null || email.isBlank()) throw new IllegalArgumentException("E-mail não pode ser vazio");
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) throw new IllegalArgumentException("E-mail inválido");
    }
}
