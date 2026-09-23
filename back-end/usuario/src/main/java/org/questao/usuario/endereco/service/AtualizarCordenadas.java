package org.questao.usuario.endereco.service;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.questao.usuario.Erros.endereco.ErroAoAtualizarCordenadasEndereco;
import org.questao.usuario.endereco.dominio.Endereco;
import org.questao.usuario.endereco.infraestrutura.EnderecoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AtualizarCordenadas {

    private static final Logger log = LoggerFactory.getLogger(AtualizarCordenadas.class);
    private final EnderecoRepository enderecoRepository;
    private final GeometryFactory geometryFactory;

    public AtualizarCordenadas(EnderecoRepository enderecoRepository,  GeometryFactory geometryFactory) {
        this.enderecoRepository = enderecoRepository;
        this.geometryFactory = geometryFactory;
    }

    public Endereco atualizarCordenadas(Long id, Double latitude, Double longitude) {
        try {
            Endereco endereco = enderecoRepository.buscar(id);
            Coordinate coordinate = new Coordinate(longitude, latitude);
            Point point = geometryFactory.createPoint(coordinate);
            point.setSRID(4326);
            endereco.atualizarCordenadas(point);
            return enderecoRepository.salvar(endereco);
        }
        catch (Exception e) {
            log.error("Erro ao atualizar cordenadas do endereco: ", e.getMessage());
            throw new ErroAoAtualizarCordenadasEndereco(e);
        }
    }
}
