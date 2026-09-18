package org.questao.entregador.entregador.infraestrutura;

import org.questao.entregador.entregador.dominio.Entregador;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntregadorRepositoryAdapter implements EntregadorRepository {

    private final EntregadorJPARepository entregadorJPARepository;

    public EntregadorRepositoryAdapter(EntregadorJPARepository entregadorJPARepository) {
        this.entregadorJPARepository = entregadorJPARepository;
    }

    @Override
    public List<Entregador> listar() {
        List<EntregadorEntity> entregadores = entregadorJPARepository.findAll();
        return entregadores.stream().map(EntregadorMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Entregador buscar(Long id) {
        EntregadorEntity empresa = entregadorJPARepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Entregador não encontrado: " + id));
        return EntregadorMapper.toDomain(empresa);
    }

    @Override
    public Entregador salvar(Entregador entregador) {
        EntregadorEntity entregadorEntity = EntregadorMapper.toEntity(entregador);
        return EntregadorMapper.toDomain(entregadorJPARepository.save(entregadorEntity));
    }

    @Override
    public Entregador atualizar(Long id, Entregador dados) {
        Entregador entregador = buscar(id);
        entregador.atualizar(dados);
        return salvar(entregador);
    }

    @Override
    public void excluir(Long id) {
        Entregador entregador = buscar(id);
        entregadorJPARepository.delete(EntregadorMapper.toEntity(entregador));
    }

    @Override
    public Entregador buscarPorCpf(String cpf) {
        EntregadorEntity entregadorEntity = entregadorJPARepository.findByCpf(cpf);
        return EntregadorMapper.toDomain(entregadorEntity);
    }
}
