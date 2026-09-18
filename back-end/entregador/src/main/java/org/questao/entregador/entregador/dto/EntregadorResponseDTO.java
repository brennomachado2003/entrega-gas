package org.questao.entregador.entregador.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntregadorResponseDTO {

    private Long idEntregador;
    private String nome;
    private String telefone;
    private Boolean ativo;
}