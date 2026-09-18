package org.questao.logistica.logistica.buscarLocalizacao.client;

import org.questao.logistica.logistica.buscarLocalizacao.dto.CoordenadaDTO;
import org.questao.logistica.logistica.buscarLocalizacao.dto.GoogleLocation;
import org.questao.logistica.logistica.buscarLocalizacao.dto.GoogleResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GoogleGeocodingClient {

    @Value("${google.maps.api-key}")
    private String apiKey;//Colocar a key correta

    private final RestClient restClient;

    public GoogleGeocodingClient(RestClient.Builder builder) {
        this.restClient = builder.build();
    }

    public CoordenadaDTO buscarCoordenadas(String endereco) {
        try {
            GoogleResponse response =
                    restClient.get()
                            .uri(uriBuilder -> uriBuilder
                                    .scheme("https")
                                    .host("maps.googleapis.com")
                                    .path("/maps/api/geocode/json")
                                    .queryParam("address", endereco)
                                    .queryParam("key", apiKey)
                                    .build())
                            .retrieve()
                            .body(GoogleResponse.class);

            if (response == null) return null;
            if (response.getResults() == null || response.getResults().isEmpty()) return null;
            GoogleLocation location = response.getResults().getFirst().getGeometry().getLocation();
            return new CoordenadaDTO(location.getLat(), location.getLng());

        } catch (Exception e) {
            System.out.println("Erro ao buscar coordenadas:");
            return null;
        }
    }
}
