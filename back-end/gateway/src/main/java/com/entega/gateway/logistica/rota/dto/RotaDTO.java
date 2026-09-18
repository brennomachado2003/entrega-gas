package com.entega.gateway.logistica.rota.dto;

public record RotaDTO(
        String polyline,
        Integer distanciaMetros,
        Integer duracaoSegundos
) {
}