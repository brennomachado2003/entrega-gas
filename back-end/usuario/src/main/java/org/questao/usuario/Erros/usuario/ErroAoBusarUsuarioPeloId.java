package org.questao.usuario.Erros.usuario;

public class ErroAoBusarUsuarioPeloId extends RuntimeException {
    public ErroAoBusarUsuarioPeloId(Exception e) {
        super("Erro ao buscar usuario pelo id: " + e);
    }
}
