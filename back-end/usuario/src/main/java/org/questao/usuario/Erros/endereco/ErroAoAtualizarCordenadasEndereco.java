package org.questao.usuario.Erros.endereco;

public class ErroAoAtualizarCordenadasEndereco extends RuntimeException {
    public ErroAoAtualizarCordenadasEndereco(Exception e) {
        super("Erro ao atualizar cordenadas do endereco: " + e);
    }
}
