package com.entega.gateway.estoque.movimentacao.api;

import com.entega.gateway.estoque.estoqueMovel.dto.EstoqueMovelRequestDTO;
import com.entega.gateway.estoque.estoqueMovel.dto.EstoqueMovelResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "estoque", contextId = "movimentacaoAPI")
public interface MovimentacaoAPI {

    @PostMapping("/estoqueMovel")
    ResponseEntity<EstoqueMovelResponseDTO> cadastrar(@RequestBody EstoqueMovelRequestDTO operacaoEntregaRequestDTO);
}
