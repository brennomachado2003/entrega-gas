package org.questao.estoque.estoque.estoqueMovel.service;

import org.questao.estoque.estoque.estoqueMovel.dominio.EstoqueMovel;
import org.questao.estoque.estoque.estoqueMovel.infraestrutura.EstoqueMovelRepository;
import org.springframework.stereotype.Service;

@Service
public class CadastrarEstoqueMovelService {

    private final EstoqueMovelRepository estoqueMovelRepository;

    public CadastrarEstoqueMovelService(EstoqueMovelRepository estoqueMovelRepository) {
        this.estoqueMovelRepository = estoqueMovelRepository;
    }

    public EstoqueMovel cadastrar(EstoqueMovel estoqueMovel) {
        return estoqueMovelRepository.salvar(estoqueMovel);
    }
}
