package org.questao.estoque.estoque.estoqueMovel.controller;


import org.questao.estoque.estoque.estoqueMovel.dominio.EstoqueMovel;
import org.questao.estoque.estoque.estoqueMovel.dto.EstoqueMovelRequestDTO;
import org.questao.estoque.estoque.estoqueMovel.dto.EstoqueMovelResponseDTO;
import org.questao.estoque.estoque.estoqueMovel.infraestrutura.EstoqueMovelMapper;
import org.questao.estoque.estoque.estoqueMovel.service.CadastrarEstoqueMovelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estoqueMovel")
public class EstoqueMovelController {

    private final CadastrarEstoqueMovelService cadastrarEstoqueMovelService;

    public EstoqueMovelController(CadastrarEstoqueMovelService cadastrarEstoqueMovelService) {
        this.cadastrarEstoqueMovelService = cadastrarEstoqueMovelService;
    }

    @PostMapping
    public ResponseEntity<EstoqueMovelResponseDTO> cadastrar(@RequestBody EstoqueMovelRequestDTO operacaoEntregaRequestDTO) {
        EstoqueMovel estoqueMovel = EstoqueMovelMapper.estoqueMovelRequestDTO(operacaoEntregaRequestDTO);
        EstoqueMovel salvo = cadastrarEstoqueMovelService.cadastrar(estoqueMovel);
        return ResponseEntity.status(HttpStatus.CREATED).body(EstoqueMovelMapper.movimentcaoResponseDTO(salvo));
    }
}
