package org.questao.empresa.empresa.service;

import org.questao.empresa.empresa.dominio.Empresa;
import org.questao.empresa.empresa.infraestrutura.EmpresaRepository;

import java.util.List;

public class ListaEmpresaService {

    private final EmpresaRepository empresaRepository;

    public ListaEmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public List<Empresa> listaEmpresas() {
        return empresaRepository.listar();
    }

}
