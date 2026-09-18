package com.entega.gateway.notificacao.dto;
import java.time.LocalDateTime;


public record SolicitacaoEntregaResponseDTO(
     Long idSolicitacao,
     Long idEntregador,
     StatusSolicitacao status,
     LocalDateTime dataSolicitacao
){
}