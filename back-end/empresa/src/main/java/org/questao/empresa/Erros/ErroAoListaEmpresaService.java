package org.questao.empresa.Erros;

public class ErroAoListaEmpresaService extends RuntimeException {
    public ErroAoListaEmpresaService() {
        super("Erro listar empresa");
    }
}
