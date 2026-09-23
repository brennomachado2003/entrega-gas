package org.questao.empresa.Erros;

public class ErroAoBuscarEmpresaService extends RuntimeException {
    public ErroAoBuscarEmpresaService() {
        super("Erro ao buscar empresa pelo identificador");
    }
}
