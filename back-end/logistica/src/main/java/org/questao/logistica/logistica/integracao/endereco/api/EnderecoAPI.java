package org.questao.logistica.logistica.integracao.endereco.api;

import org.locationtech.jts.geom.Point;
import org.questao.logistica.logistica.integracao.endereco.dto.EnderecoRequestDTO;
import org.questao.logistica.logistica.integracao.endereco.dto.EnderecoResponseDTO;
import org.questao.logistica.logistica.integracao.endereco.dto.LocalizacaoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "usuario", contextId = "enderecoAPI")
public interface EnderecoAPI {

    @PostMapping("/enderecos")
    ResponseEntity<EnderecoResponseDTO> cadastrar(@RequestBody EnderecoRequestDTO dto);

    @GetMapping("/enderecos/usuario/{id}")
    ResponseEntity<List<EnderecoResponseDTO>> buscarPorUsuario(@PathVariable Long id);

    @GetMapping("/enderecos/{id}")
    ResponseEntity<LocalizacaoResponseDTO> buscarEndereco(@PathVariable Long id);
}
