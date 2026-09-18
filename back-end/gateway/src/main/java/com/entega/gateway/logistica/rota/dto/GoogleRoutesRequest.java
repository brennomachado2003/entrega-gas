package com.entega.gateway.logistica.rota.dto;

public record GoogleRoutesRequest(
        Waypoint origin,
        Waypoint destination,
        String travelMode
) {

    public GoogleRoutesRequest(
            Double origemLat,
            Double origemLng,
            Double destinoLat,
            Double destinoLng
    ) {
        this(
                new Waypoint(new Location(new LatLng(origemLat, origemLng))), new Waypoint(new Location(new LatLng(destinoLat, destinoLng))),
                "DRIVE"
        );
    }

    public record Waypoint(
            Location location
    ) {}

    public record Location(
            LatLng latLng
    ) {}

    public record LatLng(
            Double latitude,
            Double longitude
    ) {}
}