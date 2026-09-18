package org.questao.logistica.logistica.integracao.endereco.dto;


public record EnderecoResponseDTO (
        Long idEndereco,
        String rua,
        String numero,
        String bairro,
        String cidade,
        String estado
){
}
