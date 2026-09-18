package org.questao.logistica.logistica.rastreamento.service;

import org.questao.logistica.logistica.rastreamento.dominio.PosicaoEntregador;
import org.questao.logistica.logistica.rastreamento.infraestrutura.PosicaoEntregadorRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarPosicaoPeloEntregador {

    private final PosicaoEntregadorRepository repository;

    public BuscarPosicaoPeloEntregador(PosicaoEntregadorRepository repository) {
        this.repository = repository;
    }

    public PosicaoEntregador buscarPeloEntregador(Long id) {
        return repository.buscarEntregadorPorId(id);
    }


}
