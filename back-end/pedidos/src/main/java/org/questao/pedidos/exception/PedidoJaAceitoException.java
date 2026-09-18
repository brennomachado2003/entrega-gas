package org.questao.pedidos.exception;

public class PedidoJaAceitoException extends RuntimeException {

    public PedidoJaAceitoException() {
        super("Pedido já foi aceito por outro entregador.");
    }
}
