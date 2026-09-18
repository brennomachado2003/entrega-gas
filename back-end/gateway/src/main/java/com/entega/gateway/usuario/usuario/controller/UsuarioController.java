package com.entega.gateway.usuario.usuario.controller;


import com.entega.gateway.usuario.usuario.api.UsuarioAPI;
import com.entega.gateway.usuario.usuario.dto.LoginRequest;
import com.entega.gateway.usuario.usuario.dto.LoginResponse;
import com.entega.gateway.usuario.usuario.dto.UsuarioCadastroDTO;
import com.entega.gateway.usuario.usuario.dto.UsuarioResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioAPI usuarioAPI;

    public UsuarioController(UsuarioAPI usuarioAPI) {
        this.usuarioAPI = usuarioAPI;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody UsuarioCadastroDTO dto){
        return usuarioAPI.cadastrar(dto);
    };

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        return usuarioAPI.login(request);
    };

}
