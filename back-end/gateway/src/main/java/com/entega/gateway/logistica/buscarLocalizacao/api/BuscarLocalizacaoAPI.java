package com.entega.gateway.logistica.buscarLocalizacao.api;



import com.entega.gateway.logistica.buscarLocalizacao.dto.CoordenadaDTO;
import com.entega.gateway.logistica.buscarLocalizacao.dto.EnderecoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "logistica", contextId = "buscarLocalizacaoAPI")
public interface BuscarLocalizacaoAPI {

    @PostMapping("/buscar")
    CoordenadaDTO buscarLatitudeLongitude(@RequestBody EnderecoDTO endereco);

}
