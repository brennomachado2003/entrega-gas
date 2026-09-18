package org.questao.pedidos.itemPedido.dominio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public record Desconto(BigDecimal valor) {

    public Desconto {
        Objects.requireNonNull(valor, "O desconto não pode ser nulo");
        if (valor.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("O desconto deve ser maior ou igual a zero");
        valor = valor.setScale(2, RoundingMode.HALF_UP);
    }
}


