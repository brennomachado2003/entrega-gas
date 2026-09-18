package org.questao.entregador.entregador.controller;

import org.questao.entregador.entregador.dominio.Entregador;
import org.questao.entregador.entregador.dto.EntregadorCadastroDTO;
import org.questao.entregador.entregador.dto.EntregadorResponseDTO;
import org.questao.entregador.entregador.infraestrutura.EntregadorMapper;
import org.questao.entregador.entregador.service.AlterarStatusOnlineService;
import org.questao.entregador.entregador.service.BuscarEntregadorService;
import org.questao.entregador.entregador.service.CadastrarEntregadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/entregadores")
public class EntregadorController {

    private final CadastrarEntregadorService cadastrarEntregadorService;
    private final AlterarStatusOnlineService alterarStatusOnlineService;
    private final BuscarEntregadorService buscarEntregadorService;

    public EntregadorController(CadastrarEntregadorService cadastrarEntregadorService,
                                AlterarStatusOnlineService alterarStatusOnlineService,
                                BuscarEntregadorService buscarEntregadorService) {
        this.cadastrarEntregadorService = cadastrarEntregadorService;
        this.alterarStatusOnlineService = alterarStatusOnlineService;
        this.buscarEntregadorService = buscarEntregadorService;

    }

    @PostMapping("/cadastro")
    public ResponseEntity<EntregadorResponseDTO> cadastrar(@RequestBody EntregadorCadastroDTO dto) {
        Entregador entregador = EntregadorMapper.requestToDominio(dto);
        entregador.setCriadoEm(LocalDateTime.now());
        Entregador salvo = cadastrarEntregadorService.cadastrar(entregador);
        return ResponseEntity.status(HttpStatus.CREATED).body(EntregadorMapper.entregadorResponseDTO(salvo));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<EntregadorResponseDTO> alternarStatusOnline(@PathVariable Long id) {
        Entregador entregador = buscarEntregadorService.buscarEntregadorPorId(id);
        return ResponseEntity.ok(EntregadorMapper.entregadorResponseDTO(alterarStatusOnlineService.alterarStatusOnline(entregador)));
    }




}