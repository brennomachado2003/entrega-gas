package org.questao.usuario.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private Long id;
    private String nome;
    private String contato;
    private String tipo;
    private boolean ativo;
}
