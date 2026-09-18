package com.entega.gateway.empresa.dto;

public record EmpresaCadastroDTO (
     String razaoSocial,
     String cnpj,
     String telefone,
     String rua,
     String numero,
     String complemento,
     String bairro,
     String cidade,
     String estado,
     String cep,
     Double latitude,
     Double longitude
){
    @Override
    public String toString() {
        return String.format(
                "%s, %s, %s, %s, %s",
                rua,
                numero,
                bairro,
                cidade,
                cep
        );
    }
}