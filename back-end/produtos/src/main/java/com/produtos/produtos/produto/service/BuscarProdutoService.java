package com.produtos.produtos.produto.service;

import com.produtos.produtos.produto.dominio.Produto;
import com.produtos.produtos.produto.infraestrutura.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarProdutoService {

    private final ProdutoRepository produtoRepository;

    public  BuscarProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto buscarProdutoPorId(Long idProduto) {
        return produtoRepository.buscar(idProduto);
    }
}
