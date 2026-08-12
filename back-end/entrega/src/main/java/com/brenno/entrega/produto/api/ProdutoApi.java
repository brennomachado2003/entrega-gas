package com.brenno.entrega.produto.api;

import com.brenno.entrega.produto.dto.ProdutoRequestDTO;
import com.brenno.entrega.produto.dto.ProdutoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "produtos")
public interface ProdutoApi {

    @PostMapping("/produtos")
    ProdutoResponseDTO cadastrar(@RequestBody ProdutoRequestDTO dto);

    @GetMapping("/produtos")
    List<ProdutoResponseDTO> listar();

    @GetMapping("/produtos/{id}")
    ProdutoResponseDTO buscar(@PathVariable Integer id);

    @PutMapping("/produtos/{id}")
    ProdutoResponseDTO atualizar(@PathVariable Integer id, @RequestBody ProdutoRequestDTO dto);
}