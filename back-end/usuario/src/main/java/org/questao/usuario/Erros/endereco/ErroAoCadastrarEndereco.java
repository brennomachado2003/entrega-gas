package org.questao.usuario.Erros.endereco;

public class ErroAoCadastrarEndereco extends RuntimeException {
    public ErroAoCadastrarEndereco(Exception e) {
        super("Erro ao cadastrar endereco: " + e);
    }
}
