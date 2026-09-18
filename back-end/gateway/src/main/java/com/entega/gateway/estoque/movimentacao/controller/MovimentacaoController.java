package com.entega.gateway.estoque.movimentacao.controller;


import com.entega.gateway.estoque.estoqueMovel.dto.EstoqueMovelRequestDTO;
import com.entega.gateway.estoque.estoqueMovel.dto.EstoqueMovelResponseDTO;
import com.entega.gateway.estoque.movimentacao.api.MovimentacaoAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {

    private final MovimentacaoAPI movimentacaoAPI;

    public MovimentacaoController(MovimentacaoAPI movimentacaoAPI) {
        this.movimentacaoAPI = movimentacaoAPI;
    }

    @PostMapping
    public ResponseEntity<EstoqueMovelResponseDTO> cadastrar(@RequestBody EstoqueMovelRequestDTO operacaoEntregaRequestDTO) {
        return movimentacaoAPI.cadastrar(operacaoEntregaRequestDTO);
    }


}
