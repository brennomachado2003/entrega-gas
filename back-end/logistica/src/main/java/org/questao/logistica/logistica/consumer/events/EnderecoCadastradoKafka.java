package org.questao.logistica.logistica.consumer.events;

public record EnderecoCadastradoKafka(
        Long enderecoId,
        String rua,
        Integer numero,
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
