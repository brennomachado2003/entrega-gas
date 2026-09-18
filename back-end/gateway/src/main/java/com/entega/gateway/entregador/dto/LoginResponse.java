package com.entega.gateway.entregador.dto;


public record LoginResponse (
     Long id,
     String nome,
     String contato,
     String tipo,
     boolean ativo
){
}
