package org.questao.empresa.empresa.controller;

import org.questao.empresa.empresa.dominio.Empresa;
import org.questao.empresa.empresa.dto.EmpresaCadastroDTO;
import org.questao.empresa.empresa.dto.EmpresaResponseDTO;
import org.questao.empresa.empresa.infraestrutura.EmpresaMapper;
import org.questao.empresa.empresa.service.CadastrarEmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    private final CadastrarEmpresaService cadastrarEmpresaService;

    public EmpresaController(CadastrarEmpresaService cadastrarEmpresaService) {
        this.cadastrarEmpresaService = cadastrarEmpresaService;
    }

    @PostMapping
    public ResponseEntity<EmpresaResponseDTO> cadastrar(@RequestBody EmpresaCadastroDTO dto) {
        Empresa empresa = cadastrarEmpresaService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(EmpresaMapper.entregadorResponseDTO(empresa));
    }
}
