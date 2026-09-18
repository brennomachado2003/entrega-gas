package com.entega.gateway.logistica.rota.dto;

public record GoogleRoutesResponse(
        java.util.List<Route> routes
) {

    public record Route(
            Integer distanceMeters,
            String duration,
            Polyline polyline
    ) {}

    public record Polyline(
            String encodedPolyline
    ) {}
}
