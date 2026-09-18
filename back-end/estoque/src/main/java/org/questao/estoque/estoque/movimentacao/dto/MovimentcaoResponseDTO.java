package org.questao.estoque.estoque.movimentacao.dto;

import lombok.Data;

import java.time.LocalDateTime;

public record MovimentcaoResponseDTO (
     Long idOperacaoEntrega,
     Long idPedido,
     LocalDateTime dataEntrega,
     int quantidadeBotijaoCheio,
     int quantidadeBotijaoVazio,
     int quantidadeBotijaoCompleto,
     String tipoMovimentacao
){

}
