package org.questao.entregador.Erros;

public class ErroAoFazerLoginEntregador extends RuntimeException {
    public ErroAoFazerLoginEntregador(Exception ex) {
        super("Erro ao tentar fazer login entregador: " + ex.getMessage());
    }
}
