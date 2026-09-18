package org.questao.estoque.estoque.estoqueMovel.dto;


import java.time.LocalDateTime;

public record EstoqueMovelResponseDTO (
     Long id,
     Long idEntregador,
     LocalDateTime dataHora
){
}
