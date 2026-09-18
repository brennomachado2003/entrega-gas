package com.entega.gateway.usuario.endereco.controller;


import com.entega.gateway.usuario.endereco.api.EnderecoAPI;
import com.entega.gateway.usuario.endereco.dto.EnderecoRequestDTO;
import com.entega.gateway.usuario.endereco.dto.EnderecoResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoAPI enderecoAPI;

    public EnderecoController(EnderecoAPI enderecoAPI) {
        this.enderecoAPI = enderecoAPI;
    }

    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> cadastrar(@RequestBody EnderecoRequestDTO dto){
        return enderecoAPI.cadastrar(dto);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<EnderecoResponseDTO>> buscarPorUsuario(@PathVariable Long id){
        return enderecoAPI.buscarPorUsuario(id);
    }

    @GetMapping("/endereco/{id}")
    public ResponseEntity<EnderecoResponseDTO> buscarEnderecoId(@PathVariable Long id) {
        return enderecoAPI.buscarEnderecoId(id);
    }

}
