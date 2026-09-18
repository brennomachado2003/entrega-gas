package org.questao.usuario.usuario.dto;


public record UsuarioResponseDTO (
        Long idUsuario,
        String nome,
        String email
){
}
