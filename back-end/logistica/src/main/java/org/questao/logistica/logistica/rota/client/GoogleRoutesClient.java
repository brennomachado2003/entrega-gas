package org.questao.logistica.logistica.rota.client;


import org.questao.logistica.logistica.rota.dto.GoogleRoutesRequest;
import org.questao.logistica.logistica.rota.dto.GoogleRoutesResponse;
import org.questao.logistica.logistica.rota.dto.RotaDTO;
import org.questao.logistica.logistica.rota.dto.RotaRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GoogleRoutesClient {

    @Value("${google.maps.api-key}")
    private String apiKey;
    private final RestClient restClient;
    public GoogleRoutesClient(RestClient.Builder builder) {
        this.restClient = builder.build();
    }

    public RotaDTO calcularRota(RotaRequestDTO request) {
        GoogleRoutesRequest body = new GoogleRoutesRequest(request.origemLatitude(), request.origemLongitude(), request.destinoLatitude(), request.destinoLongitude());
        GoogleRoutesResponse response = acessarAPIGoogleRoutes(body);
        GoogleRoutesResponse.Route route = response.routes().getFirst();
        Integer duracaoSegundos = Integer.parseInt(route.duration().replace("s", ""));
        return new RotaDTO(route.polyline().encodedPolyline(), route.distanceMeters(), duracaoSegundos);
    }

    public GoogleRoutesResponse acessarAPIGoogleRoutes(GoogleRoutesRequest body) {
        GoogleRoutesResponse response =
                restClient.post()
                        .uri("https://routes.googleapis.com/directions/v2:computeRoutes")
                        .header("X-Goog-Api-Key", apiKey)
                        .header("X-Goog-FieldMask", "routes.distanceMeters,routes.duration,routes.polyline.encodedPolyline")
                        .body(body)
                        .retrieve()
                        .body(GoogleRoutesResponse.class);
        if (response == null || response.routes() == null || response.routes().isEmpty()) throw new RuntimeException("Rota não encontrada");
        return response;
    }
}
