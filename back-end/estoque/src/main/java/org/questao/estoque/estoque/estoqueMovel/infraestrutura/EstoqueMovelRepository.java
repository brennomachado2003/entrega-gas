package org.questao.estoque.estoque.estoqueMovel.infraestrutura;

import org.questao.estoque.estoque.estoqueMovel.dominio.EstoqueMovel;

import java.util.List;

public interface EstoqueMovelRepository {

     List<EstoqueMovel> listar();
     EstoqueMovel buscar(Long id);
     EstoqueMovel salvar(EstoqueMovel estoqueMovel);
}
