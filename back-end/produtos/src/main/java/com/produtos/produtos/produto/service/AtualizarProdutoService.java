package com.produtos.produtos.produto.service;

import com.produtos.produtos.produto.dominio.Produto;
import com.produtos.produtos.produto.infraestrutura.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizarProdutoService {

    private final ProdutoRepository produtoRepository;

    public AtualizarProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto atualizar(Long id, Produto dados){
        Produto produto = produtoRepository.buscar(id);
        produto.atualizar(dados);
        return produtoRepository.salvar(produto);
    }
}
