package org.questao.estoque.estoque.estoqueMovel.infraestrutura;


import org.questao.estoque.estoque.estoqueMovel.dominio.EstoqueMovel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstoqueMovelRepositoryAdapter implements EstoqueMovelRepository {

    private final EstoqueMovelJPARepository estoqueMovelJPARepository;

    public EstoqueMovelRepositoryAdapter(EstoqueMovelJPARepository estoqueMovelJPARepository) {
        this.estoqueMovelJPARepository = estoqueMovelJPARepository;
    }

    @Override
    public List<EstoqueMovel> listar() {
        List<EstoqueMovelEntity> estoqueMovel = estoqueMovelJPARepository.findAll();
        return estoqueMovel.stream().map(EstoqueMovelMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public EstoqueMovel buscar(Long id) {
        EstoqueMovelEntity estoqueMovel = estoqueMovelJPARepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Estoque movel não encontrado: " + id));
        return EstoqueMovelMapper.toDomain(estoqueMovel);
    }

    @Override
    public EstoqueMovel salvar(EstoqueMovel estoqueMovel) {
        EstoqueMovelEntity estoqueMovelEntity = EstoqueMovelMapper.toEntity(estoqueMovel);
        return EstoqueMovelMapper.toDomain(estoqueMovelJPARepository.save(estoqueMovelEntity));
    }
}
