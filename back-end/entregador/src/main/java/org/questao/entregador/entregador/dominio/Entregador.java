package org.questao.entregador.entregador.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.questao.entregador.entregador.infraestrutura.events.EntregadorCadastrado;
import org.questao.entregador.publicador.AggregateRoot;

import java.awt.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Entregador extends AggregateRoot {

    private final Long idEntregador;
    private String nome;
    private Cpf cpf;
    private Senha senha;
    private Telefone telefone;
    private Boolean ativo;
    private LocalDateTime criadoEm;

    public Entregador(Long idEntregador, String nome, Cpf cpf, Senha senha, Telefone telefone, Boolean ativo) {
        this.idEntregador = idEntregador;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.telefone = telefone;
        this.ativo = ativo;
        this.criadoEm = LocalDateTime.now();
    }
    public static Entregador reconstituir(Long idEntregador, String nome, String cpf, String senha, String telefone, Boolean ativo,  LocalDateTime criadoEm) {
        return new Entregador(idEntregador, nome, new Cpf(cpf), new Senha(senha), new Telefone(telefone), ativo, criadoEm);
    }
    public void atualizar(Entregador dados) {
        this.nome = dados.getNome();
        this.cpf = dados.getCpf();
        this.senha = dados.getSenha();
        this.telefone = dados.getTelefone();
        this.ativo = dados.getAtivo();
    }
    public void entregadorCadastrado(){
        register(new EntregadorCadastrado(idEntregador));
    }
}
