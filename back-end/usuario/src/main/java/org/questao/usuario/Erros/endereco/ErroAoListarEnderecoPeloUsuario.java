package org.questao.usuario.Erros.endereco;

public class ErroAoListarEnderecoPeloUsuario extends RuntimeException {
    public ErroAoListarEnderecoPeloUsuario(Exception e) {
        super("Erro ao listar endereco pelo usuario: " + e);
    }
}
