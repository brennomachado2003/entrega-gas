package org.questao.empresa.empresa.service;

import org.questao.empresa.empresa.dominio.Empresa;
import org.questao.empresa.empresa.infraestrutura.EmpresaRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarEmpresaService {

    private final EmpresaRepository empresaRepository;

    public  BuscarEmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa buscar(Long id) {
        return empresaRepository.buscar(id);
    }
}
