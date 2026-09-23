package org.questao.usuario.Erros.usuario;

public class ErroAoCadastrarUsuario extends RuntimeException {
    public ErroAoCadastrarUsuario(Exception e) {
        super("Erro ao cadastrar usuario: " + e);
    }
}
