package org.questao.usuario.endereco.dto;


import org.questao.usuario.endereco.dominio.Endereco;

public record EnderecoResponseDTO (
        Long idEndereco,
        String rua,
        String numero,
        String bairro,
        String cidade,
        String estado
){
}
