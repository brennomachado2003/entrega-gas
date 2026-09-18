package com.entega.gateway.usuario.usuario.dto;

import lombok.Value;

import java.sql.Date;

@Value
public class UsuarioCadastroDTO {

    private String nome;
    private String cpf;
    private Date dataNascimento;
    private String telefone;
    private String email;
    private String senha;

}