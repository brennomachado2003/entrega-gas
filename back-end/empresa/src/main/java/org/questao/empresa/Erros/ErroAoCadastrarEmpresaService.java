package org.questao.empresa.Erros;

public class ErroAoCadastrarEmpresaService extends RuntimeException {
    public ErroAoCadastrarEmpresaService() {
        super("Erro ao cadastrar empresa");
    }
}
