package org.questao.estoque.estoque.movimentacao.infraestrutura;

import org.questao.estoque.estoque.movimentacao.dominio.Movimentacao;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovimentacaoRepositoryAdapter implements MovimentacaoRepository {

    private final MovimentacaoJPARepository movimentacaoJPARepository;

    public MovimentacaoRepositoryAdapter(MovimentacaoJPARepository movimentacaoJPARepository) {
        this.movimentacaoJPARepository = movimentacaoJPARepository;
    }

    @Override
    public List<Movimentacao> listar() {
        List<MovimentacaoEntity> empresas = movimentacaoJPARepository.findAll();
        return empresas.stream().map(MovimentacaoMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Movimentacao buscar(Long id) {
        MovimentacaoEntity movimentacao = movimentacaoJPARepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Movimentacao não encontrado: " + id));
        return MovimentacaoMapper.toDomain(movimentacao);
    }

    @Override
    public Movimentacao salvar(Movimentacao movimentacao) {
        MovimentacaoEntity movimentacaoEntity = MovimentacaoMapper.toEntity(movimentacao);
        return MovimentacaoMapper.toDomain(movimentacaoJPARepository.save(movimentacaoEntity));
    }

    @Override
    public Movimentacao atualizar(Long id, Movimentacao dados) {
        Movimentacao movimentacao = buscar(id);
        movimentacao.atualizar(dados);
        return salvar(movimentacao);
    }
}
