package com.entega.gateway.entregador.api;


import com.entega.gateway.entregador.dto.EntregadorCadastroDTO;
import com.entega.gateway.entregador.dto.EntregadorResponseDTO;
import com.entega.gateway.entregador.dto.LoginRequest;
import com.entega.gateway.entregador.dto.LoginResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "entregador",
        contextId = "entregadorAPI",
        url = "http://localhost:8086"
)
public interface EntregadorAPI {

    @PostMapping("/entregadores/cadastro")
    ResponseEntity<EntregadorResponseDTO> cadastrar(@RequestBody EntregadorCadastroDTO dto);

    @PatchMapping("/entregadores/{id}/status")
    ResponseEntity<EntregadorResponseDTO> alternarStatusOnline(
            @PathVariable("id") Long id
    );
    @PostMapping("/auth/login/entregador")
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request);
}
