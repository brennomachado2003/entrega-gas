package com.entega.gateway.usuario.endereco.dto;


public record EnderecoResponseDTO (
        Long idEndereco,
        String rua,
        String numero,
        String bairro,
        String cidade,
        String estado
){
}
