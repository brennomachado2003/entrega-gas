package org.questao.empresa.empresa.infraestrutura;

import org.locationtech.jts.geom.Point;
import org.questao.empresa.empresa.dominio.Empresa;
import org.questao.empresa.empresa.dto.EmpresaCadastroDTO;
import org.questao.empresa.empresa.dto.EmpresaResponseDTO;

import java.time.LocalDateTime;

public final class EmpresaMapper {

    private EmpresaMapper() {
    }

    public static EmpresaEntity toEntity(Empresa empresa) {
        return new EmpresaEntity(
                empresa.getIdEmpresa(),
                empresa.getRazaoSocial(),
                empresa.getCnpj().cnpj(),
                empresa.getTelefone().telefone(),
                empresa.getLocalizacao()

        );
    }

    public static Empresa toDomain(EmpresaEntity entity) {
        return Empresa.reconstituir(
                entity.getIdEmpresa(),
                entity.getRazaoSocial(),
                entity.getCnpj(),
                entity.getTelefone(),
                entity.getLocalizacao()
        );
    }

    public static EmpresaResponseDTO entregadorResponseDTO(Empresa empresa) {
        return new EmpresaResponseDTO(empresa.getIdEmpresa(), empresa.getRazaoSocial(), empresa.getTelefone().telefone());
    }

    public static Empresa requestToDominio(EmpresaCadastroDTO empresaCadastroDTO, Point localizacao) {
        return Empresa.reconstituir(
                null,
                empresaCadastroDTO.razaoSocial(),
                empresaCadastroDTO.cnpj(),
                empresaCadastroDTO.telefone(),
                localizacao
        );
    }
}
