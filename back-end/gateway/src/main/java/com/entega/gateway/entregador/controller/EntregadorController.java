package com.entega.gateway.entregador.controller;

import com.entega.gateway.entregador.api.EntregadorAPI;
import com.entega.gateway.entregador.dto.EntregadorCadastroDTO;
import com.entega.gateway.entregador.dto.EntregadorResponseDTO;
import com.entega.gateway.entregador.dto.LoginRequest;
import com.entega.gateway.entregador.dto.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entregadores")
public class EntregadorController {

    private final EntregadorAPI entregadorAPI;

    public EntregadorController(EntregadorAPI entregadorAPI) {
        this.entregadorAPI = entregadorAPI;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<EntregadorResponseDTO> cadastrar(@RequestBody EntregadorCadastroDTO dto) {
        return entregadorAPI.cadastrar(dto);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<EntregadorResponseDTO> alternarStatusOnline(@PathVariable Long id) {
        return entregadorAPI.alternarStatusOnline(id);
    }

    @PostMapping("/login/entregador")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return entregadorAPI.login(request);
    }


}
