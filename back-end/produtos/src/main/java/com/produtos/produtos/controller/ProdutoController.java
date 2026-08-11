package com.brenno.entrega.produto.controller;

import com.brenno.entrega.produto.dto.ProdutoRequestDTO;
import com.brenno.entrega.produto.dto.ProdutoResponseDTO;
import com.brenno.entrega.produto.model.Produto;
import com.brenno.entrega.produto.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrar(@RequestBody ProdutoRequestDTO dto) {
        Produto salvo = produtoService.cadastrar(dto);
        ProdutoResponseDTO response = new ProdutoResponseDTO(salvo.getIdProduto(), salvo.getNome(), salvo.getValor());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listar() {
        List<ProdutoResponseDTO> produtos = produtoService.findAll();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscar(@PathVariable Integer id) {
        ProdutoResponseDTO response = produtoService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Integer id, @RequestBody ProdutoRequestDTO dto) {
        ProdutoResponseDTO response = produtoService.update(id, dto);
        return ResponseEntity.ok(response);
    }


}