package com.produtos.produtos.produto.service;

import com.produtos.produtos.produto.dominio.Produto;
import com.produtos.produtos.produto.infraestrutura.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class CadastrarProdutoService {

    private final ProdutoRepository produtoRepository;

    public CadastrarProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto cadastroProduto(Produto produto) {
        return produtoRepository.salvar(produto);
    }
}
