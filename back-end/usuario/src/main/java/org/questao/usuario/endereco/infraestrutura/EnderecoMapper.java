package org.questao.usuario.endereco.infraestrutura;

import org.locationtech.jts.geom.Point;
import org.questao.usuario.endereco.dominio.Endereco;
import org.questao.usuario.endereco.dto.EnderecoRequestDTO;
import org.questao.usuario.endereco.dto.EnderecoResponseDTO;
import org.questao.usuario.usuario.dominio.Usuario;
import org.questao.usuario.usuario.infraestrutura.UsuarioMapper;

public final class EnderecoMapper {

    private EnderecoMapper() {
    }

    public static EnderecoEntity toEntity(Endereco endereco) {

        return new EnderecoEntity(
                endereco.getIdEndereco(),
                UsuarioMapper.toEntity(endereco.getUsuario()),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep().cep(),
                endereco.getLocalizacao()
        );
    }

    public static Endereco toDomain(EnderecoEntity entity) {

        return Endereco.reconstituir(
                entity.getIdEndereco(),
                UsuarioMapper.toDomain(entity.getUsuario()),
                entity.getRua(),
                entity.getNumero(),
                entity.getComplemento(),
                entity.getBairro(),
                entity.getCidade(),
                entity.getEstado(),
                entity.getCep(),
                entity.getLocalizacao()
        );
    }

    public static EnderecoResponseDTO enderecoResponseDTO(Endereco endereco) {
        return new EnderecoResponseDTO(endereco.getIdEndereco(), endereco.getRua(), endereco.getNumero(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado());
    }

    public static Endereco requestToDominio(EnderecoRequestDTO enderecoRequestDTO, Usuario usuario) {
        return Endereco.reconstituir(
                null,
                usuario,
                enderecoRequestDTO.getRua(),
                enderecoRequestDTO.getNumero(),
                enderecoRequestDTO.getComplemento(),
                enderecoRequestDTO.getBairro(),
                enderecoRequestDTO.getCidade(),
                enderecoRequestDTO.getEstado(),
                enderecoRequestDTO.getCep(),
                null

        );
    }
}
