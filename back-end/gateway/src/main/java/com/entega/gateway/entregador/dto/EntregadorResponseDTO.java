package com.entega.gateway.entregador.dto;


public record EntregadorResponseDTO (

     Long idEntregador,
     String nome,
     String telefone,
     Boolean ativo
){
}