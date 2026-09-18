package org.questao.logistica.logistica.rastreamento.service;

import org.questao.logistica.logistica.rastreamento.dominio.PosicaoEntregador;
import org.questao.logistica.logistica.rastreamento.infraestrutura.PosicaoEntregadorRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarPosicaoEntregadorService {

    private final PosicaoEntregadorRepository posicaoEntregadorRepository;

    public CriarPosicaoEntregadorService(PosicaoEntregadorRepository posicaoEntregadorRepository){
        this.posicaoEntregadorRepository = posicaoEntregadorRepository;
    }

    public PosicaoEntregador cadastrar(PosicaoEntregador posicaoEntregador) {
        return posicaoEntregadorRepository.salvar(posicaoEntregador);
    }
}
