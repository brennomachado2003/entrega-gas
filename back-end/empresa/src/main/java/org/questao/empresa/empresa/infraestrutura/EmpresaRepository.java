package org.questao.empresa.empresa.infraestrutura;

import org.questao.empresa.empresa.dominio.Empresa;

import java.util.List;

public interface EmpresaRepository {

    public List<Empresa> listar();

    public Empresa buscar(Long id);

    public Empresa salvar(Empresa empresa);

    public Empresa atualizar(Long id, Empresa dados);

    public void excluir(Long id);
}
