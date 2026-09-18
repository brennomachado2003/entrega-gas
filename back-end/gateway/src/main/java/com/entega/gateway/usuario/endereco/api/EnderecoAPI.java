package com.entega.gateway.usuario.endereco.api;

import com.entega.gateway.usuario.endereco.dto.EnderecoRequestDTO;
import com.entega.gateway.usuario.endereco.dto.EnderecoResponseDTO;
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

    @GetMapping("/enderecos/endereco/{id}")
    ResponseEntity<EnderecoResponseDTO> buscarEnderecoId(@PathVariable Long id);
}
