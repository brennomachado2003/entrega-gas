package com.entega.gateway.empresa.controller;

import com.entega.gateway.empresa.api.EmpresaAPI;
import com.entega.gateway.empresa.dto.EmpresaCadastroDTO;
import com.entega.gateway.empresa.dto.EmpresaResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    private final EmpresaAPI empresaAPI;

    public EmpresaController(EmpresaAPI empresaAPI) {
        this.empresaAPI = empresaAPI;
    }

    @PostMapping
    public ResponseEntity<EmpresaResponseDTO> cadastrar(@RequestBody EmpresaCadastroDTO dto) {
        return empresaAPI.cadastrar(dto);
    }
}
