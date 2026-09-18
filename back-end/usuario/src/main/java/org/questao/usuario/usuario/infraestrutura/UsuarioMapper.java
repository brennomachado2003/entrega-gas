package org.questao.usuario.usuario.infraestrutura;

import org.questao.usuario.usuario.dominio.Usuario;
import org.questao.usuario.usuario.dto.UsuarioCadastroDTO;
import org.questao.usuario.usuario.dto.UsuarioResponseDTO;

public final class UsuarioMapper {

    private UsuarioMapper() {
    }

    public static UsuarioEntity toEntity(Usuario usuario) {

        return new UsuarioEntity(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getCpf().cpf(),
                usuario.getDataNascimento(),
                usuario.getTelefone().telefone(),
                usuario.getEmail().email(),
                usuario.getSenha().senha(),
                usuario.isAtivo()
        );
    }

    public static Usuario toDomain(UsuarioEntity entity) {

        return Usuario.reconstituir(
                entity.getIdUsuario(),
                entity.getNome(),
                entity.getCpf(),
                entity.getDataNascimento(),
                entity.getTelefone(),
                entity.getEmail(),
                entity.getSenha(),
                entity.isAtivo()
        );
    }

    public static UsuarioResponseDTO usuarioResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(usuario.getIdUsuario(), usuario.getNome(), usuario.getEmail().email());
    }

    public static Usuario requestToDominio(UsuarioCadastroDTO usuarioCadastroDTO) {
        return Usuario.reconstituir(
                null,
                usuarioCadastroDTO.getNome(),
                usuarioCadastroDTO.getCpf(),
                usuarioCadastroDTO.getDataNascimento(),
                usuarioCadastroDTO.getTelefone(),
                usuarioCadastroDTO.getEmail(),
                usuarioCadastroDTO.getSenha(),
                true
        );
    }
}
