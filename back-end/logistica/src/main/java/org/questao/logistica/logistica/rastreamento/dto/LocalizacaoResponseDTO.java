package org.questao.logistica.logistica.rastreamento.dto;

import java.time.LocalDateTime;

public record LocalizacaoResponseDTO(
        Long idEntregador,
        Double latitude,
        Double longitude,
        LocalDateTime data
) {
}
