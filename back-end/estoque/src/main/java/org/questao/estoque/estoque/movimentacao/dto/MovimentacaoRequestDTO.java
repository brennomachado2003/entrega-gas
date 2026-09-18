package org.questao.estoque.estoque.movimentacao.dto;


import org.questao.estoque.estoque.movimentacao.dominio.TipoMovimentacao;

import java.time.LocalDateTime;

public record MovimentacaoRequestDTO (
     Long idPedido,
     TipoMovimentacao tipoMovimentacao,
     LocalDateTime dataEntrega,
     int quantidadeBotijaoCheio,
     int quantidadeBotijaoVazio,
     int quantidadeBotijaoCompleto,
     String observacao,
     Long operacaoEntrega
){

}
