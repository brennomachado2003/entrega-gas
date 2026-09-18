package com.entega.gateway.logistica.rota.controller;


import com.entega.gateway.logistica.rota.api.RotaAPI;
import com.entega.gateway.logistica.rota.dto.RotaDTO;
import com.entega.gateway.logistica.rota.dto.RotaRequestDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rotas")
public class RotaController {

    private final RotaAPI rotaAPI;

    public RotaController(RotaAPI rotaAPI) {
        this.rotaAPI = rotaAPI;
    }

    @PostMapping
    public RotaDTO calcularRota(@RequestBody RotaRequestDTO request) {
        return rotaAPI.calcularRota(request);
    }

}
