package org.questao.logistica.logistica.rastreamento.service;

import org.locationtech.jts.geom.Point;
import org.questao.logistica.logistica.rastreamento.dominio.PosicaoEntregador;
import org.questao.logistica.logistica.rastreamento.infraestrutura.PosicaoEntregadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarEntregadoresMaisProximoService {

    private final PosicaoEntregadorRepository posicaoEntregadorRepository;

    public BuscarEntregadoresMaisProximoService(PosicaoEntregadorRepository posicaoEntregadorRepository) {
        this.posicaoEntregadorRepository = posicaoEntregadorRepository;
    }

    public List<Long> buscarEntregadoresMaisProximos(Point localizacaoPedido, double raio) {
        List<PosicaoEntregador> posicaoEntregadors = posicaoEntregadorRepository.buscarEntregadoresProximos(localizacaoPedido, raio);
        return posicaoEntregadors.stream().map(PosicaoEntregador::getIdEntregador).toList();
    }
}
