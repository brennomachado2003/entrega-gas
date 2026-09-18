package com.produtos.produtos.produto.controller;


import com.produtos.produtos.produto.dominio.Produto;
import com.produtos.produtos.produto.infraestrutura.ProdutoMapper;
import com.produtos.produtos.produto.infraestrutura.dto.ProdutoRequestDTO;
import com.produtos.produtos.produto.infraestrutura.dto.ProdutoResponseDTO;
import com.produtos.produtos.produto.infraestrutura.ProdutoEntity;
import com.produtos.produtos.produto.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final CadastrarProdutoService cadastrarProdutoService;
    private final BuscarProdutoService buscarProdutoService;
    private final AtualizarProdutoService atualizarProdutoService;
    private final ListaProdutosService listaProdutosService;

    public ProdutoController(CadastrarProdutoService cadastrarProdutoService,
                             BuscarProdutoService buscarProdutoService,
                             AtualizarProdutoService atualizarProdutoService,
                             ListaProdutosService listaProdutosService) {
        this.cadastrarProdutoService = cadastrarProdutoService;
        this.buscarProdutoService = buscarProdutoService;
        this.atualizarProdutoService = atualizarProdutoService;
        this.listaProdutosService = listaProdutosService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrar(@RequestBody ProdutoRequestDTO dto) {
        Produto produto = ProdutoMapper.requestToDominio(dto);
        Produto salvo = cadastrarProdutoService.cadastroProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoMapper.produtoResponseDTO(salvo));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listar() {
        List<Produto> produtos = listaProdutosService.listaProdutos();
        List<ProdutoResponseDTO> produtosResponse = produtos.stream().map(ProdutoMapper::produtoResponseDTO).toList();
        return ResponseEntity.ok(produtosResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscar(@PathVariable Long id) {
        Produto produto = buscarProdutoService.buscarProdutoPorId(id);
        return ResponseEntity.ok(ProdutoMapper.produtoResponseDTO(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO dto) {
        Produto dados = ProdutoMapper.requestToDominio(dto);
        Produto produto = atualizarProdutoService.atualizar(id, dados);
        return ResponseEntity.ok(ProdutoMapper.produtoResponseDTO(produto));
    }

}