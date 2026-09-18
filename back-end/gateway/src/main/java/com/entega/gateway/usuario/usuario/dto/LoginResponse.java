package com.entega.gateway.usuario.usuario.dto;

public record LoginResponse (
     Long id,
     String nome,
     String contato,
     String tipo
){
}
