package org.questao.usuario.usuario.dominio;


import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Usuario {

    private Long idUsuario;
    private String nome;
    private Cpf cpf;
    private Date dataNascimento;
    private Telefone telefone;
    private Email email;
    private Senha senha;
    private boolean ativo;

    public Usuario(Long idUsuario, String nome, String cpf, Date dataNascimento, String telefone, String email, String senha, boolean ativo) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.cpf = new Cpf(cpf);
        this.dataNascimento = dataNascimento;
        this.telefone = new Telefone(telefone);
        this.email = new Email(email);
        this.senha = new Senha(senha);
        this.ativo = ativo;
    }

    public static Usuario reconstituir(Long idUsuario, String nome,  String cpf, Date dataNascimento, String telefone, String email, String senha, boolean ativo) {
        return new Usuario(idUsuario, nome, cpf, dataNascimento, telefone, email, senha, ativo);
    }

    public void atualizar(Usuario dados) {
        this.nome = dados.getNome();
        this.cpf = dados.getCpf();
        this.dataNascimento = dados.getDataNascimento();
        this.telefone = dados.getTelefone();
        this.email = dados.getEmail();
        this.senha = dados.getSenha();
        this.ativo = dados.isAtivo();
    }

    public void ativar(){
        this.ativo = true;
    }
    public void inativar(){
        this.ativo = false;
    }
}
