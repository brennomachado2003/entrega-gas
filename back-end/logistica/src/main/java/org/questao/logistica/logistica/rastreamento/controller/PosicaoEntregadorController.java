package org.questao.logistica.logistica.rastreamento.controller;


import org.questao.logistica.logistica.rastreamento.dominio.PosicaoEntregador;
import org.questao.logistica.logistica.rastreamento.dto.AtualizarLocalizacaoDTO;
import org.questao.logistica.logistica.rastreamento.dto.LocalizacaoResponseDTO;
import org.questao.logistica.logistica.rastreamento.infraestrutura.PosicaoEntregadorMapper;
import org.questao.logistica.logistica.rastreamento.service.AtualizarLocalizacaoService;
import org.questao.logistica.logistica.rastreamento.service.BuscarPosicaoEntregadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/localizacao")
public class PosicaoEntregadorController {
    private final AtualizarLocalizacaoService atualizarLocalizacaoService;

    public PosicaoEntregadorController(AtualizarLocalizacaoService atualizarLocalizacaoService, BuscarPosicaoEntregadorService buscarPosicaoEntregadorService) {
        this.atualizarLocalizacaoService = atualizarLocalizacaoService;

    }


    @PostMapping("/{id}/localizacao")
    public ResponseEntity<LocalizacaoResponseDTO> atualizarLocalizacao(@PathVariable Long id, @RequestBody AtualizarLocalizacaoDTO dto) {
        PosicaoEntregador posicaoEntregador = atualizarLocalizacaoService.atualizarLocalizacaoEntregado(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(PosicaoEntregadorMapper.toResponse(posicaoEntregador));
    }

}
