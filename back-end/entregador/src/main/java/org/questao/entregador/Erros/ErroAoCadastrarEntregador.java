package org.questao.entregador.Erros;

public class ErroAoCadastrarEntregador extends RuntimeException {
    public ErroAoCadastrarEntregador(Exception ex) {
        super("Erro ao cadastrar entregador: " + ex.getMessage());
    }
}
