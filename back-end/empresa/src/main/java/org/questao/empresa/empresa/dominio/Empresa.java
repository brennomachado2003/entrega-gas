package org.questao.empresa.empresa.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class Empresa {

    private final Long idEmpresa;
    private String razaoSocial;
    private Cnpj cnpj;
    private Telefone telefone;
    private Point localizacao;

    public static Empresa reconstituir(Long idEmpresa, String razaoSocial, String cnpj, String telefone, Point localizacao) {
        return new Empresa(idEmpresa, razaoSocial, new Cnpj(cnpj), new Telefone(telefone), localizacao);
    }

    public void atualizar(Empresa dados) {
        this.razaoSocial = dados.razaoSocial;
        this.cnpj = dados.cnpj;
        this.telefone = dados.telefone;
        this.localizacao = dados.localizacao;
    }
}
