package com.brenno.entrega.produto.service;

import com.brenno.entrega.produto.dto.ProdutoRequestDTO;
import com.brenno.entrega.produto.dto.ProdutoResponseDTO;
import com.brenno.entrega.produto.model.Produto;
import com.brenno.entrega.produto.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    public final ProdutoRepository produtoRepository;
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto save(Produto produto){
        return produtoRepository.save(produto);
    }

    public List<ProdutoResponseDTO> findAll() {
        return produtoRepository.findAll().stream().map(produto -> new ProdutoResponseDTO(produto.getIdProduto(), produto.getNome(), produto.getValor())).toList();
    }

    public Produto findById(Integer id) {
        return produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public void delete(Produto produto) {
        produtoRepository.delete(produto);
    }

    public ProdutoResponseDTO update(Integer id, ProdutoRequestDTO dto) {
        Produto produto = findById(id);
        produto.setNome(dto.getNome());
        produto.setNcm(dto.getNcm());
        produto.setQuantidade(dto.getQuantidade());
        produto.setValor(dto.getValor());
        Produto atualizado = save(produto);
        return new ProdutoResponseDTO(atualizado.getIdProduto(), atualizado.getNome(), atualizado.getValor());
    }

    public Produto cadastrar(ProdutoRequestDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setNcm(dto.getNcm());
        produto.setQuantidade(dto.getQuantidade());
        produto.setValor(dto.getValor());
        return produtoRepository.save(produto);
    }

    public ProdutoResponseDTO buscarPorId(Integer id) {
        Produto produto = findById(id);
        return new ProdutoResponseDTO(produto.getIdProduto(), produto.getNome(), produto.getValor());
    }
}
