package org.questao.estoque.estoque.movimentacao.infraestrutura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoJPARepository extends JpaRepository<MovimentacaoEntity, Long> {
    /*
    save(movimentacao)
    findById(id)
    findAll()
    deleteById(id)
    delete(movimentacao)
    existsById(id)
    count()
    findAllById(ids)
    */
}
