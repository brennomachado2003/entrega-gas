package org.questao.usuario.endereco.infraestrutura.kafka;

import org.questao.usuario.endereco.dominio.CEP;
import org.questao.usuario.publicador.DomainEvent;

import java.time.Instant;

public record EnderecoCadastradoKafka(
        Long enderecoId,
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep,
        Instant ocorridoEm

) implements DomainEvent {
    public EnderecoCadastradoKafka(Long enderecoId, String rua, String numero, String complemento, String bairro, String cidade, String estado, String cep) {
        this(enderecoId, rua, numero, complemento, bairro, cidade, estado, cep,  Instant.now());
    }
}
