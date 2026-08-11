package com.brenno.entrega.produto.repository;

import com.brenno.entrega.produto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
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
