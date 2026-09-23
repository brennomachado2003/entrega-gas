package org.questao.empresa.empresa.service;

import org.questao.empresa.Erros.ErroAoBuscarEmpresaService;
import org.questao.empresa.empresa.controller.EmpresaController;
import org.questao.empresa.empresa.dominio.Empresa;
import org.questao.empresa.empresa.infraestrutura.EmpresaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BuscarEmpresaService {

    private static final Logger log = LoggerFactory.getLogger(BuscarEmpresaService.class);
    private final EmpresaRepository empresaRepository;

    public  BuscarEmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa buscar(Long id) {
        try {
            return empresaRepository.buscar(id);
        }
        catch (Exception e) {
            log.error("Erro ao buscar empresa: ", e.getMessage());
            throw new ErroAoBuscarEmpresaService();
        }

    }
}
