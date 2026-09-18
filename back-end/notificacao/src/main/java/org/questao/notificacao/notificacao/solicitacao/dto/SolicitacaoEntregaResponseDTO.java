package org.questao.notificacao.notificacao.solicitacao.dto;


import org.questao.notificacao.notificacao.solicitacao.dominio.StatusSolicitacao;

import java.time.LocalDateTime;


public record SolicitacaoEntregaResponseDTO (
     Long idSolicitacao,
     Long idEntregador,
     StatusSolicitacao status,
     LocalDateTime dataSolicitacao
){
}