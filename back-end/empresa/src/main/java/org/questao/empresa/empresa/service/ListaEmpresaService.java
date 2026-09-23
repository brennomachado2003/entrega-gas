package org.questao.empresa.empresa.service;

import org.questao.empresa.Erros.ErroAoListaEmpresaService;
import org.questao.empresa.empresa.controller.EmpresaController;
import org.questao.empresa.empresa.dominio.Empresa;
import org.questao.empresa.empresa.infraestrutura.EmpresaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ListaEmpresaService {

    private static final Logger log = LoggerFactory.getLogger(ListaEmpresaService.class);
    private final EmpresaRepository empresaRepository;

    public ListaEmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public List<Empresa> listaEmpresas() {
        try {
            return empresaRepository.listar();
        }
        catch (Exception e) {
            log.error("Erro ao listar empresas: ", e.getMessage());
            throw new ErroAoListaEmpresaService();
        }
    }

}
