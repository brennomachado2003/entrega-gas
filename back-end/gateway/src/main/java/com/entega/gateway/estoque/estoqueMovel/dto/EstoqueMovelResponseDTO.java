package com.entega.gateway.estoque.estoqueMovel.dto;


import java.time.LocalDateTime;

public record EstoqueMovelResponseDTO (
     Long id,
     Long idEntregador,
     LocalDateTime dataHora
){
}
