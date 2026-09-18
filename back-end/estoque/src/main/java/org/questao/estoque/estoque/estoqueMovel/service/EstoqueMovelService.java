package org.questao.estoque.estoque.estoqueMovel.service;


import org.questao.estoque.estoque.estoqueMovel.infraestrutura.EstoqueMovelJPARepository;
import org.springframework.stereotype.Service;

@Service
public class EstoqueMovelService {

    private final EstoqueMovelJPARepository operacaoEntregaRepository;

    public EstoqueMovelService(EstoqueMovelJPARepository operacaoEntregaRepository) {
        this.operacaoEntregaRepository = operacaoEntregaRepository;
    }


}
