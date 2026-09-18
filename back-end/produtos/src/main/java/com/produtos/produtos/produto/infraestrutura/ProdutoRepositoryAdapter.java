package com.produtos.produtos.produto.infraestrutura;

import com.produtos.produtos.produto.dominio.Produto;
import com.produtos.produtos.produto.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoRepositoryAdapter implements ProdutoRepository {

    private final ProdutoJPARepository produtoJPARepository;

    public ProdutoRepositoryAdapter(ProdutoJPARepository produtoJPARepository) {
        this.produtoJPARepository = produtoJPARepository;
    }

    @Override
    public List<Produto> listar() {
        List<ProdutoEntity> produtos = produtoJPARepository.findAll();
        return produtos.stream().map(ProdutoMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Produto buscar(Long id) {
        ProdutoEntity produto = produtoJPARepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado: " + id));
        return ProdutoMapper.toDomain(produto);
    }

    @Override
    public Produto salvar(Produto produto) {
        ProdutoEntity produtoEntity = ProdutoMapper.toEntity(produto);
        return ProdutoMapper.toDomain(produtoJPARepository.save(produtoEntity));
    }

    @Override
    public Produto atualizar(Long id, Produto dados) {
        Produto produto = buscar(id);
        produto.atualizar(dados);
        return salvar(produto);
    }

    @Override
    public void excluir(Long id) {
        Produto produto = buscar(id);
        produtoJPARepository.delete(ProdutoMapper.toEntity(produto));
    }

    @Override
    public BigDecimal precoProduto(Long id) {
        Produto produto = buscar(id);
        return produto.getValor().valor();
    }
}
