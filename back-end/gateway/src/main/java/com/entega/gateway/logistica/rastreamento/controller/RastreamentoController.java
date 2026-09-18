package com.entega.gateway.logistica.rastreamento.controller;


import com.entega.gateway.logistica.rastreamento.api.RastreamentoAPI;
import com.entega.gateway.logistica.rastreamento.dto.AtualizarLocalizacaoDTO;
import com.entega.gateway.logistica.rastreamento.dto.LocalizacaoResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/localizacao")
public class RastreamentoController {

    private final RastreamentoAPI rastreamentoAPI;

    public RastreamentoController(RastreamentoAPI rastreamentoAPI) {
        this.rastreamentoAPI = rastreamentoAPI;
    }

    @PostMapping("/{id}/localizacao")
    public ResponseEntity<LocalizacaoResponseDTO> atualizarLocalizacao(@PathVariable Long id, @RequestBody AtualizarLocalizacaoDTO dto) {
        return rastreamentoAPI.atualizarLocalizacao(id, dto);
    }

}
