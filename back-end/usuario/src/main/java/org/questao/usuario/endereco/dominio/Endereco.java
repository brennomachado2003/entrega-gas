package org.questao.usuario.endereco.dominio;


import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;
import org.questao.usuario.endereco.infraestrutura.events.EnderecoCadastrado;
import org.questao.usuario.publicador.AggregateRoot;
import org.questao.usuario.usuario.dominio.Usuario;


@Getter
@Setter
public class Endereco extends AggregateRoot {

    private final Long idEndereco;
    private final Usuario usuario;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private CEP cep;
    private Point localizacao;

    public Endereco(Long idEndereco, Usuario usuario, String rua, String numero, String complemento, String bairro, String cidade, String estado, String cep, Point localizacao) {
        this.idEndereco = idEndereco;
        this.usuario = usuario;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = new CEP(cep);
        this.localizacao = localizacao;
    }

    public static Endereco reconstituir(Long idEndereco, Usuario usuario,
                                        String rua, String numero,
                                        String complemento, String bairro,
                                        String cidade, String estado,
                                        String cep, Point localizacao) {
        return new Endereco(idEndereco, usuario, rua, numero, complemento, bairro, cidade, estado, cep, localizacao);
    }

    public void atualizar(Endereco dados) {
        this.rua = dados.getRua();
        this.numero = dados.getNumero();
        this.complemento = dados.getComplemento();
        this.bairro = dados.getBairro();
        this.cidade = dados.getCidade();
        this.estado = dados.getEstado();
        this.cep = dados.getCep();
        this.localizacao = dados.getLocalizacao();
    }

    public void enderecoCadastrado(Endereco endereco){
        register(new EnderecoCadastrado(idEndereco, rua, numero, complemento, bairro, cidade, estado, cep));
    }

    public void atualizarCordenadas(Point localizacao) {
        this.localizacao = localizacao;
    }
}
