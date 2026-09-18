package com.entega.gateway.logistica.rota.api;



import com.entega.gateway.logistica.rota.dto.RotaDTO;
import com.entega.gateway.logistica.rota.dto.RotaRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "logistica", contextId = "rotaAPI")
public interface RotaAPI {

    @PostMapping("/rotas")
    RotaDTO calcularRota(@RequestBody RotaRequestDTO request);
}
