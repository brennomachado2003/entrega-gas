package org.questao.logistica.logistica.rastreamento.service;

import org.questao.logistica.logistica.rastreamento.dominio.PosicaoEntregador;
import org.questao.logistica.logistica.rastreamento.infraestrutura.PosicaoEntregadorRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarPosicaoEntregadorService {

    private final PosicaoEntregadorRepository posicaoEntregadorRepository;

    public BuscarPosicaoEntregadorService(PosicaoEntregadorRepository posicaoEntregadorRepository) {
        this.posicaoEntregadorRepository = posicaoEntregadorRepository;
    }

    public PosicaoEntregador buscarEntregador(Long entregadorId) {
        return posicaoEntregadorRepository.buscar(entregadorId);
    }
}
