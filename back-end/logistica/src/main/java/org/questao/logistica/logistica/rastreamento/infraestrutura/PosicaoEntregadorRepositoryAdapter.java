package org.questao.logistica.logistica.rastreamento.infraestrutura;

import org.locationtech.jts.geom.Point;
import org.questao.logistica.logistica.rastreamento.dominio.PosicaoEntregador;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PosicaoEntregadorRepositoryAdapter implements PosicaoEntregadorRepository {

    private final PosicaoEntregadorJPARepository posicaoEntregadorJPARepository;

    public PosicaoEntregadorRepositoryAdapter(PosicaoEntregadorJPARepository posicaoEntregadorJPARepository) {
        this.posicaoEntregadorJPARepository = posicaoEntregadorJPARepository;
    }

    @Override
    public List<PosicaoEntregador> listar() {
        List<PosicaoEntregadorEntity> posicaoEntregadorEntity = posicaoEntregadorJPARepository.findAll();
        return posicaoEntregadorEntity.stream().map(PosicaoEntregadorMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public PosicaoEntregador buscar(Long id) {
        PosicaoEntregadorEntity posicaoEntregadorEntity = posicaoEntregadorJPARepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Possicao entregador não encontrado: " + id));
        return PosicaoEntregadorMapper.toDomain(posicaoEntregadorEntity);
    }

    @Override
    public PosicaoEntregador salvar(PosicaoEntregador posicaoEntregador) {
        PosicaoEntregadorEntity posicaoEntregadorEntity = PosicaoEntregadorMapper.toEntity(posicaoEntregador);
        return PosicaoEntregadorMapper.toDomain(posicaoEntregadorJPARepository.save(posicaoEntregadorEntity));
    }

    @Override
    public PosicaoEntregador atualizar(Long id, Point localizacao) {
        PosicaoEntregador posicaoEntregador = buscar(id);
        posicaoEntregador.atualizar(localizacao);
        return salvar(posicaoEntregador);
    }

    @Override
    public List<PosicaoEntregador> buscarEntregadoresProximos(Point localizacao, double raio) {
        List<PosicaoEntregadorEntity> posicaoEntregadorEntityList = posicaoEntregadorJPARepository.findEntregadoresProximos(localizacao, raio);
        return posicaoEntregadorEntityList.stream().map(PosicaoEntregadorMapper::toDomain).toList();
    }

    @Override
    public PosicaoEntregador buscarEntregadorPorId(Long id) {
        return PosicaoEntregadorMapper.toDomain(posicaoEntregadorJPARepository.findByEntregador(id));
    }

}
