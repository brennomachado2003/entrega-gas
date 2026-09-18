package com.produtos.produtos.produto.infraestrutura;

import com.produtos.produtos.produto.dominio.Produto;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository {
    List<Produto> listar();
    Produto buscar(Long id);
    Produto salvar(Produto produto);
    Produto atualizar(Long id, Produto dados);
    void excluir(Long id);
    BigDecimal precoProduto(Long id);
}
