package com.produtos.produtos.produto.infraestrutura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoJPARepository extends JpaRepository<ProdutoEntity, Long> {
    /*
    save(produto)
    findById(id)
    findAll()
    deleteById(id)
    delete(produto)
    existsById(id)
    count()
    findAllById(ids)
    */
}
