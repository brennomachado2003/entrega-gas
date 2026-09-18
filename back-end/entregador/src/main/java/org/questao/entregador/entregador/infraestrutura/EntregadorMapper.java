package org.questao.entregador.entregador.infraestrutura;

import org.questao.entregador.entregador.dominio.Entregador;
import org.questao.entregador.entregador.dto.EntregadorCadastroDTO;
import org.questao.entregador.entregador.dto.EntregadorResponseDTO;

import java.time.LocalDateTime;

public final class EntregadorMapper
{
    private EntregadorMapper() {
    }

    public static EntregadorEntity toEntity(Entregador entregador) {
        return new EntregadorEntity(
                entregador.getIdEntregador(),
                entregador.getNome(),
                entregador.getCpf().cpf(),
                entregador.getSenha().senha(),
                entregador.getTelefone().telefone(),
                entregador.getAtivo(),
                entregador.getCriadoEm()

        );
    }

    public static Entregador toDomain(EntregadorEntity entity) {

        return Entregador.reconstituir(
                entity.getIdEntregador(),
                entity.getNome(),
                entity.getCpf(),
                entity.getSenha(),
                entity.getTelefone(),
                entity.getAtivo(),
                entity.getCriadoEm()
        );
    }

    public static EntregadorResponseDTO entregadorResponseDTO(Entregador entregador) {
        return new EntregadorResponseDTO(entregador.getIdEntregador(), entregador.getNome(), entregador.getTelefone().telefone(), entregador.getAtivo());
    }

    public static Entregador requestToDominio(EntregadorCadastroDTO entregadorCadastroDTO) {
        return Entregador.reconstituir(
                null,
                entregadorCadastroDTO.getNome(),
                entregadorCadastroDTO.getCpf(),
                entregadorCadastroDTO.getSenha(),
                entregadorCadastroDTO.getTelefone(),
                true,
                LocalDateTime.now()
        );
    }
}
