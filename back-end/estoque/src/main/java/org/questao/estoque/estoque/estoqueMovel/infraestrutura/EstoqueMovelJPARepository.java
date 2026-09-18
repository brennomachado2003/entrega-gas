package org.questao.estoque.estoque.estoqueMovel.infraestrutura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueMovelJPARepository extends JpaRepository<EstoqueMovelEntity, Long> {

}
