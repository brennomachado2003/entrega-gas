package org.questao.usuario.usuario.controller;

import org.questao.usuario.Erros.usuario.ErroAoCadastrarUsuario;
import org.questao.usuario.endereco.controller.EnderecoController;
import org.questao.usuario.usuario.dominio.Usuario;
import org.questao.usuario.usuario.dto.UsuarioCadastroDTO;
import org.questao.usuario.usuario.dto.UsuarioResponseDTO;
import org.questao.usuario.usuario.infraestrutura.UsuarioMapper;
import org.questao.usuario.usuario.service.CadastrarUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final CadastrarUsuarioService cadastrarUsuarioService;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    public UsuarioController(CadastrarUsuarioService cadastrarUsuarioService) {
        this.cadastrarUsuarioService = cadastrarUsuarioService;
    }

    @PostMapping("/cadastro")
    @ExceptionHandler(ErroAoCadastrarUsuario.class)
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody UsuarioCadastroDTO dto) {
        logger.info("Requisicao cadastrando usuario recebida{}", dto);
        Usuario usuario = UsuarioMapper.requestToDominio(dto);
        Usuario salvo = cadastrarUsuarioService.cadastrar(usuario);
        if (salvo == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        UsuarioResponseDTO response = UsuarioMapper.usuarioResponseDTO(salvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
