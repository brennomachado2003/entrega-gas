package com.brenno.entrega.buscarLocalizacao.client;

import com.brenno.entrega.buscarLocalizacao.dto.CoordenadaDTO;
import com.brenno.entrega.buscarLocalizacao.dto.GoogleLocation;
import com.brenno.entrega.buscarLocalizacao.dto.GoogleResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

            if (response == null) {
                System.out.println("Resposta da Google é null");
                return null;
            }

            System.out.println("STATUS GOOGLE: " + response.getStatus());

            if (response.getResults() == null ||
                    response.getResults().isEmpty()) {

                System.out.println("Google não encontrou o endereço.");
                return null;
            }

            GoogleLocation location = response
                    .getResults()
                    .getFirst()
                    .getGeometry()
                    .getLocation();

            return new CoordenadaDTO(
                    location.getLat(),
                    location.getLng()
            );

        } catch (Exception e) {
            System.out.println("Erro ao buscar coordenadas:");
            e.printStackTrace();
            return null;
        }
    }
}
