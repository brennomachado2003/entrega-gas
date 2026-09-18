package com.entega.gateway.usuario.usuario.api;

import com.entega.gateway.usuario.usuario.dto.LoginRequest;
import com.entega.gateway.usuario.usuario.dto.LoginResponse;
import com.entega.gateway.usuario.usuario.dto.UsuarioCadastroDTO;
import com.entega.gateway.usuario.usuario.dto.UsuarioResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "usuario", contextId = "usuarioAPI")
public interface UsuarioAPI {

    @PostMapping("/usuarios/cadastro")
    ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody UsuarioCadastroDTO dto);

    @PostMapping("/auth/login")
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request);
}
