package com.entega.gateway.logistica.buscarLocalizacao.controller;


import com.entega.gateway.logistica.buscarLocalizacao.api.BuscarLocalizacaoAPI;
import com.entega.gateway.logistica.buscarLocalizacao.dto.CoordenadaDTO;
import com.entega.gateway.logistica.buscarLocalizacao.dto.EnderecoDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buscarLocalizacao")
public class BuscarLocalizacaoController {

    private final BuscarLocalizacaoAPI buscarLocalizacaoAPI;

    public BuscarLocalizacaoController(BuscarLocalizacaoAPI buscarLocalizacaoAPI) {
        this.buscarLocalizacaoAPI = buscarLocalizacaoAPI;
    }

    @PostMapping
    public CoordenadaDTO buscarLatitudeLongitude(@RequestBody EnderecoDTO endereco) {
        return buscarLocalizacaoAPI.buscarLatitudeLongitude(endereco);
    }


}
