package org.questao.usuario.endereco.controller;

import org.locationtech.jts.geom.Point;
import org.questao.usuario.endereco.dominio.Endereco;
import org.questao.usuario.endereco.dto.EnderecoRequestDTO;
import org.questao.usuario.endereco.dto.EnderecoResponseDTO;
import org.questao.usuario.endereco.dto.LocalizacaoResponseDTO;
import org.questao.usuario.endereco.infraestrutura.EnderecoMapper;
import org.questao.usuario.endereco.service.BuscarEnderecoService;
import org.questao.usuario.endereco.service.CadastrarEnderecoService;
import org.questao.usuario.endereco.service.ListaEnderecoService;
import org.questao.usuario.usuario.dominio.Usuario;
import org.questao.usuario.usuario.service.BuscarUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final CadastrarEnderecoService cadastrarEnderecoService;
    private final BuscarUsuarioService buscarUsuarioService;
    private final ListaEnderecoService listaEnderecoService;
    private final BuscarEnderecoService buscarEnderecoService;


    public EnderecoController(CadastrarEnderecoService cadastrarEnderecoService,
                              BuscarUsuarioService buscarUsuarioService,
                              ListaEnderecoService listaEnderecoService,
                              BuscarEnderecoService buscarEnderecoService) {
        this.cadastrarEnderecoService = cadastrarEnderecoService;
        this.buscarUsuarioService = buscarUsuarioService;
        this.listaEnderecoService = listaEnderecoService;
        this.buscarEnderecoService = buscarEnderecoService;
    }

    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> cadastrar(@RequestBody EnderecoRequestDTO dto) {
        Usuario usuario = buscarUsuarioService.buscarPorId(dto.getUsuarioId());
        Endereco endereco = EnderecoMapper.requestToDominio(dto, usuario);
        Endereco salvo = cadastrarEnderecoService.cadastrar(endereco);
        EnderecoResponseDTO enderecoResponseDTO = EnderecoMapper.enderecoResponseDTO(salvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoResponseDTO);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<EnderecoResponseDTO>> buscarPorUsuario(@PathVariable Long id) {
        Usuario usuario = buscarUsuarioService.buscarPorId(id);
        List<Endereco> enderecos = listaEnderecoService.listEndereco(usuario);
        List<EnderecoResponseDTO> response = enderecos.stream().map(EnderecoMapper::enderecoResponseDTO).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalizacaoResponseDTO> buscarEndereco(@PathVariable Long id) {
        Endereco endereco = buscarEnderecoService.buscar(id);
        Point point = endereco.getLocalizacao();

        LocalizacaoResponseDTO response = new LocalizacaoResponseDTO(
                point.getY(), // latitude
                point.getX()  // longitude
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/endereco/{id}")
    public ResponseEntity<EnderecoResponseDTO> buscarEnderecoId(@PathVariable Long id) {
        Endereco endereco = buscarEnderecoService.buscar(id);
        return ResponseEntity.ok(EnderecoMapper.enderecoResponseDTO(endereco));
    }


}
