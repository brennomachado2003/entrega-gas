package com.brenno.entrega.user.endereco.dto;

import lombok.Data;

@Data
public class EnderecoRequestDTO {
    private Integer usuarioId;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private Double latitude;
    private Double longitude;

    @Override
    public String toString() {
        return String.format(
                "%s, %s, %s, %s, %s, %s, Brasil",
                rua,
                numero,
                bairro,
                cidade,
                estado,
                cep
        );
    }
}
