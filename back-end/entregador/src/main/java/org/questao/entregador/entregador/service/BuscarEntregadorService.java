package org.questao.entregador.entregador.service;

import org.questao.entregador.entregador.dominio.Entregador;
import org.questao.entregador.entregador.infraestrutura.EntregadorRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarEntregadorService {

    private final EntregadorRepository entregadorRepository;

    public BuscarEntregadorService(EntregadorRepository entregadorRepository) {
        this.entregadorRepository = entregadorRepository;
    }

    public Entregador buscarEntregadorPorId(Long id) {
        return entregadorRepository.buscar(id);
    }

}
