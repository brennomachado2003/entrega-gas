package com.entega.gateway.logistica.rastreamento.api;



import com.entega.gateway.logistica.rastreamento.dto.AtualizarLocalizacaoDTO;
import com.entega.gateway.logistica.rastreamento.dto.LocalizacaoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "logistica", contextId = "localizacaoAPI")
public interface RastreamentoAPI {

    @PostMapping("/localizacao/{id}/localizacao")
    ResponseEntity<LocalizacaoResponseDTO> atualizarLocalizacao(@PathVariable Long id, @RequestBody AtualizarLocalizacaoDTO dto);
}
