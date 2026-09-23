package org.questao.entregador.Erros;

public class ErroAoAlterarStatusOnlineService extends RuntimeException {
    public ErroAoAlterarStatusOnlineService(Exception ex) {
        super("Erro ao alterar status online: " + ex.getMessage());
    }
}
