package org.questao.logistica.logistica.rastreamento.infraestrutura;

import org.locationtech.jts.geom.Point;
import org.questao.logistica.logistica.rastreamento.dominio.PosicaoEntregador;
import org.questao.logistica.logistica.rastreamento.dto.LocalizacaoResponseDTO;

public final class PosicaoEntregadorMapper {

    private PosicaoEntregadorMapper() {}

    public static PosicaoEntregadorEntity toEntity(PosicaoEntregador posicaoEntregador) {
        return new PosicaoEntregadorEntity(
                posicaoEntregador.getIdPosicaoEntregador(),
                posicaoEntregador.getIdEntregador(),
                posicaoEntregador.getLocalizacao(),
                posicaoEntregador.getUltimaAtualizacao()

        );
    }

    public static PosicaoEntregador toDomain(PosicaoEntregadorEntity entity) {
        return PosicaoEntregador.reconstituir(
                entity.getIdPosicaoEntregador(),
                entity.getEntregador(),
                entity.getLocalizacao(),
                entity.getUltimaAtualizacao()
        );
    }

    public static LocalizacaoResponseDTO toResponse(PosicaoEntregador posicaoEntregador) {
        Point cordenadas = posicaoEntregador.getLocalizacao();

        return new LocalizacaoResponseDTO(
                posicaoEntregador.getIdEntregador(),
                cordenadas.getY(),
                cordenadas.getX(),
                posicaoEntregador.getUltimaAtualizacao()
        );
    }
}
