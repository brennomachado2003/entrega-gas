package com.entega.gateway.empresa.api;

import com.entega.gateway.empresa.dto.EmpresaCadastroDTO;
import com.entega.gateway.empresa.dto.EmpresaResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "empresa", contextId = "clienteAPI")
public interface EmpresaAPI {

    @PostMapping("/empresa")
    ResponseEntity<EmpresaResponseDTO> cadastrar(@RequestBody EmpresaCadastroDTO dto);
}
