package com.entega.gateway.estoque.movimentacao.dto;

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
