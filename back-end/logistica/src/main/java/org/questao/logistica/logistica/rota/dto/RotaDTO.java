package org.questao.logistica.logistica.rota.dto;

public record RotaDTO(
        String polyline,
        Integer distanciaMetros,
        Integer duracaoSegundos
) {
}