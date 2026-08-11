package com.brenno.entrega.produto.api;

import com.brenno.entrega.produto.dto.ProdutoRequestDTO;
import com.brenno.entrega.produto.dto.ProdutoResponseDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class ProdutoApi {

    private final RestClient restClient;

    private ProdutoApi() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8082")
                .build();
    }

    public ProdutoResponseDTO cadastrar(ProdutoRequestDTO dto) {
        return restClient.post()
                .uri("/produtos")
                .body(dto)
                .retrieve()
                .body(ProdutoResponseDTO.class);
    }

    public List<ProdutoResponseDTO> listar() {
        return restClient.get()
                .uri("/produtos")
                .retrieve()
                .body(new ParameterizedTypeReference<List<ProdutoResponseDTO>>() {});
    }

    public ProdutoResponseDTO buscar(Integer id) {
        return restClient.get()
                .uri("/produtos/{id}", id)
                .retrieve()
                .body(ProdutoResponseDTO.class);
    }

    public ProdutoResponseDTO atualizar(Integer id, ProdutoRequestDTO dto) {
        return restClient.put()
                .uri("/produtos/{id}", id)
                .body(dto)
                .retrieve()
                .body(ProdutoResponseDTO.class);
    }
}
