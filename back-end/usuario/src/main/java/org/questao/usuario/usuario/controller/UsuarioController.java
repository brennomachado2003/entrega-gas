package org.questao.usuario.usuario.controller;

import org.questao.usuario.usuario.dominio.Usuario;
import org.questao.usuario.usuario.dto.UsuarioCadastroDTO;
import org.questao.usuario.usuario.dto.UsuarioResponseDTO;
import org.questao.usuario.usuario.infraestrutura.UsuarioMapper;
import org.questao.usuario.usuario.service.CadastrarUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final CadastrarUsuarioService cadastrarUsuarioService;

    public UsuarioController(CadastrarUsuarioService cadastrarUsuarioService) {
        this.cadastrarUsuarioService = cadastrarUsuarioService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody UsuarioCadastroDTO dto) {
        Usuario usuario = UsuarioMapper.requestToDominio(dto);
        Usuario salvo = cadastrarUsuarioService.cadastrar(usuario);
        if (salvo == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        UsuarioResponseDTO response = UsuarioMapper.usuarioResponseDTO(salvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
