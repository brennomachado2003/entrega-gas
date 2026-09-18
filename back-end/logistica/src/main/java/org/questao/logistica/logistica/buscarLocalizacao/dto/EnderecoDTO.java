package org.questao.logistica.logistica.buscarLocalizacao.dto;

public record EnderecoDTO(
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep
) {
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
