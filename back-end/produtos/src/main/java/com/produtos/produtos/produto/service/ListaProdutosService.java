package com.produtos.produtos.produto.service;

import com.produtos.produtos.produto.dominio.Produto;
import com.produtos.produtos.produto.infraestrutura.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaProdutosService {

    private final ProdutoRepository produtoRepository;

    public ListaProdutosService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listaProdutos() {
        return produtoRepository.listar();
    }
}
