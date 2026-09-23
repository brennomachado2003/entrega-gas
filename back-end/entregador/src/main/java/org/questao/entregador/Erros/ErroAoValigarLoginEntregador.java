package org.questao.entregador.Erros;

public class ErroAoValigarLoginEntregador extends RuntimeException {
    public ErroAoValigarLoginEntregador(Exception ex) {
        super("Erro ao validar login entregador: " + ex.getMessage());
    }
}
