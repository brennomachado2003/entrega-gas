package org.questao.entregador.Erros;

public class ErroAoBuscarEntregadorPeloId extends RuntimeException {
    public ErroAoBuscarEntregadorPeloId(Exception ex) {
        super("Erro ao buscar entregador pelo id: " + ex.getMessage());
    }
}
