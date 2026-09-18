package com.entega.gateway.produto.controller;


import com.entega.gateway.produto.api.ProdutoAPI;
import com.entega.gateway.produto.dto.ProdutoRequestDTO;
import com.entega.gateway.produto.dto.ProdutoResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoAPI produtoAPI;

    public ProdutoController(ProdutoAPI produtoAPI) {
        this.produtoAPI = produtoAPI;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrar(@RequestBody ProdutoRequestDTO dto){
        return produtoAPI.cadastrar(dto);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listar(){
        return produtoAPI.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscar(@PathVariable Long id){
        System.out.println(id);
        return produtoAPI.buscar(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO dto){
        return produtoAPI.atualizar(id, dto);
    }


}
