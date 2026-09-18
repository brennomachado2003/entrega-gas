package org.questao.logistica.logistica.rastreamento.service;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.questao.logistica.logistica.rastreamento.dominio.PosicaoEntregador;
import org.questao.logistica.logistica.rastreamento.dto.AtualizarLocalizacaoDTO;
import org.questao.logistica.logistica.rastreamento.infraestrutura.PosicaoEntregadorRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizarLocalizacaoService {

    private final PosicaoEntregadorRepository posicaoEntregadorRepository;
    private final GeometryFactory geometryFactory;


    public  AtualizarLocalizacaoService(PosicaoEntregadorRepository posicaoEntregadorRepository, GeometryFactory geometryFactory) {
        this.posicaoEntregadorRepository = posicaoEntregadorRepository;
        this.geometryFactory = geometryFactory;
    }

    public PosicaoEntregador atualizarLocalizacaoEntregado(Long idEntregador, AtualizarLocalizacaoDTO dto) {
        PosicaoEntregador posicaoEntregador = posicaoEntregadorRepository.buscarEntregadorPorId(idEntregador);
        Point localizacao = geometryFactory.createPoint(new Coordinate(dto.longitude(), dto.latitude()));
        posicaoEntregador.setLocalizacao(localizacao);
        return posicaoEntregadorRepository.salvar(posicaoEntregador);

    }
}
