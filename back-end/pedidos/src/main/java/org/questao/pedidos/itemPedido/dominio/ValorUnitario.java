package org.questao.pedidos.itemPedido.dominio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public record ValorUnitario(BigDecimal valor) {

    public ValorUnitario {
        Objects.requireNonNull(valor, "O valor unitário não pode ser nulo");
        if (valor.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("O valor unitário deve ser maior que zero");
        valor = valor.setScale(2, RoundingMode.HALF_UP);
    }
}


