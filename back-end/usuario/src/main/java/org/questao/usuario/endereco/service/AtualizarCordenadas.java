package org.questao.usuario.endereco.service;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.questao.usuario.endereco.dominio.Endereco;
import org.questao.usuario.endereco.infraestrutura.EnderecoRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizarCordenadas {

    private final EnderecoRepository enderecoRepository;
    private final GeometryFactory geometryFactory;

    public AtualizarCordenadas(EnderecoRepository enderecoRepository,  GeometryFactory geometryFactory) {
        this.enderecoRepository = enderecoRepository;
        this.geometryFactory = geometryFactory;
    }

    public Endereco atualizarCordenadas(Long id, Double latitude, Double longitude) {
        Endereco endereco = enderecoRepository.buscar(id);
        Coordinate coordinate = new Coordinate(longitude, latitude);
        Point point = geometryFactory.createPoint(coordinate);
        point.setSRID(4326);
        endereco.atualizarCordenadas(point);
        return enderecoRepository.salvar(endereco);
    }
}
