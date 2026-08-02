package com.brenno.entrega.pedido.exception;

public class PedidoJaAceitoException extends RuntimeException {

    public PedidoJaAceitoException() {
        super("Pedido já foi aceito por outro entregador.");
    }
}
