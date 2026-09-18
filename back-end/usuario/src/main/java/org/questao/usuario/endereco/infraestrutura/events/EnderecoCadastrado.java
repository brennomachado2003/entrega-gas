package org.questao.usuario.endereco.infraestrutura.events;

import org.questao.usuario.endereco.dominio.CEP;
import org.questao.usuario.publicador.DomainEvent;

import java.time.Instant;

public record EnderecoCadastrado(
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
    public EnderecoCadastrado (Long enderecoId, String rua, String numero, String complemento, String bairro, String cidade, String estado, CEP cep) {
        this(enderecoId, rua, numero, complemento, bairro, cidade, estado, cep.cep(),  Instant.now());
    }
}
