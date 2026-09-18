package com.entega.gateway.produto.api;

import com.entega.gateway.produto.dto.ProdutoRequestDTO;
import com.entega.gateway.produto.dto.ProdutoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "produtos", contextId = "produtoAPI")
public interface ProdutoAPI {

    @PostMapping("/produtos")
    ResponseEntity<ProdutoResponseDTO> cadastrar(@RequestBody ProdutoRequestDTO dto);

    @GetMapping("/produtos")
    ResponseEntity<List<ProdutoResponseDTO>> listar();

    @GetMapping("/produtos/{id}")
    ResponseEntity<ProdutoResponseDTO> buscar(@PathVariable Long id);

    @PutMapping("/produtos/{id}")
    ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO dto);



}
