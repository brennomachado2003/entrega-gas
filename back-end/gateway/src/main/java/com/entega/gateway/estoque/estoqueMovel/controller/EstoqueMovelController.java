package com.entega.gateway.estoque.estoqueMovel.controller;


import com.entega.gateway.estoque.estoqueMovel.api.EstoqueMovelAPI;
import com.entega.gateway.estoque.estoqueMovel.dto.EstoqueMovelRequestDTO;
import com.entega.gateway.estoque.estoqueMovel.dto.EstoqueMovelResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estoqueMovel")
public class EstoqueMovelController {


    private final EstoqueMovelAPI estoqueMovelAPI;

    public EstoqueMovelController(EstoqueMovelAPI estoqueMovelAPI) {
        this.estoqueMovelAPI = estoqueMovelAPI;
    }

    @PostMapping
    public ResponseEntity<EstoqueMovelResponseDTO> cadastrar(@RequestBody EstoqueMovelRequestDTO operacaoEntregaRequestDTO) {
        return estoqueMovelAPI.cadastrar(operacaoEntregaRequestDTO);
    }


}
