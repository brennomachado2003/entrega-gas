package org.questao.usuario.Erros.usuario;

public class ErroAoFazerLogin extends RuntimeException {
    public ErroAoFazerLogin(Exception e) {
        super("Erro ao tentar fazer login: " + e);
    }
}
