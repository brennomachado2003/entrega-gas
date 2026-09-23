package org.questao.usuario.Erros.endereco;

public class ErroAoBuscarEnderecoPeloId extends RuntimeException {
    public ErroAoBuscarEnderecoPeloId(Exception e) {
        super("Erro ao buscar endereco pelo id: " + e);
    }
}
