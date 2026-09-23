package org.questao.usuario.Erros.usuario;

public class ErroAoTentarValidarLoginUsuario extends RuntimeException {
    public ErroAoTentarValidarLoginUsuario(Exception e) {
        super("Erro ao validar login usuario: " + e);
    }
}
