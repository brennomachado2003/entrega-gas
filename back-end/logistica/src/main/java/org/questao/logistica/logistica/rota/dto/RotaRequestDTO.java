package org.questao.logistica.logistica.rota.dto;

public record RotaRequestDTO(
        Double origemLatitude,
        Double origemLongitude,
        Double destinoLatitude,
        Double destinoLongitude
) {
}