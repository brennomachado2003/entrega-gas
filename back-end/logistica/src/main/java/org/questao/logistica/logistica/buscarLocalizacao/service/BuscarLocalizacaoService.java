package org.questao.logistica.logistica.buscarLocalizacao.service;


import org.questao.logistica.logistica.buscarLocalizacao.client.GoogleGeocodingClient;
import org.questao.logistica.logistica.buscarLocalizacao.dto.CoordenadaDTO;
import org.questao.logistica.logistica.buscarLocalizacao.dto.EnderecoDTO;
import org.springframework.stereotype.Service;

@Service
public class BuscarLocalizacaoService {

    private final GoogleGeocodingClient client;

    public BuscarLocalizacaoService(GoogleGeocodingClient client) {
        this.client = client;
    }

    public CoordenadaDTO buscarLocalizacao(EnderecoDTO endereco) {
        return client.buscarCoordenadas(endereco.toString());
    }
}
