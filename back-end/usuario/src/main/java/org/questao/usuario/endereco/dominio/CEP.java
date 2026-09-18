package org.questao.usuario.endereco.dominio;

public record CEP(String cep) {

    public CEP {
        if (cep == null) throw new IllegalArgumentException("CEP não pode ser nulo");
        cep = cep.replaceAll("\\D", "");
        if (cep.length() != 8) throw new IllegalArgumentException("CEP deve possuir 8 dígitos");
    }
}
