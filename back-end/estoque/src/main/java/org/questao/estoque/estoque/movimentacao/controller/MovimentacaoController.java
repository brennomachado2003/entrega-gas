package org.questao.estoque.estoque.movimentacao.controller;


import org.questao.estoque.estoque.movimentacao.dto.*;
import org.questao.estoque.estoque.movimentacao.infraestrutura.MovimentacaoMapper;
import org.questao.estoque.estoque.movimentacao.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {

    private final CargaInicialService cargaInicialService;
    private final ReabastecerService reabastecerService;
    private final VendaService vendaService;

    public MovimentacaoController(CargaInicialService cargaInicialService, ReabastecerService reabastecerService, VendaService vendaService) {
        this.cargaInicialService = cargaInicialService;
        this.reabastecerService = reabastecerService;
        this.vendaService = vendaService;
    }

    @PostMapping("/carga")
    public ResponseEntity<MovimentcaoResponseDTO> carga(@RequestBody MovimentacaoRequestDTO movimentacaoRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(MovimentacaoMapper.movimentcaoResponseDTO(cargaInicialService.CargaInicial(movimentacaoRequestDTO)));
    }

    @PostMapping("/reabastecer")
    public ResponseEntity<MovimentcaoResponseDTO> reabastecer(@RequestBody MovimentacaoRequestDTO movimentacaoRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(MovimentacaoMapper.movimentcaoResponseDTO(reabastecerService.reabastecer(movimentacaoRequestDTO)));
    }

    @PostMapping("/venda")
    public ResponseEntity<MovimentcaoResponseDTO> venda(@RequestBody MovimentacaoRequestDTO movimentacaoRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(MovimentacaoMapper.movimentcaoResponseDTO(vendaService.venda(movimentacaoRequestDTO)));
    }
}
