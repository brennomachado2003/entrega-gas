package org.questao.entregador.entregador.service;

import org.questao.entregador.entregador.dominio.Entregador;
import org.questao.entregador.entregador.infraestrutura.EntregadorRepository;
import org.springframework.stereotype.Service;

@Service
public class AlterarStatusOnlineService {

    private final EntregadorRepository entregadorRepository;

    public AlterarStatusOnlineService(EntregadorRepository entregadorRepository) {
        this.entregadorRepository = entregadorRepository;
    }

    public Entregador alterarStatusOnline(Entregador entregador) {
        entregador.setAtivo(!entregador.getAtivo());
        return entregadorRepository.salvar(entregador);
    }
}
