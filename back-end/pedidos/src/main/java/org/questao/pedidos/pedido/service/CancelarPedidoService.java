package org.questao.pedidos.pedido.service;

import org.questao.pedidos.pedido.dominio.Pedido;
import org.questao.pedidos.pedido.intraestrutura.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class CancelarPedidoService {

    private final PedidoRepository pedidoRepository;

    public CancelarPedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido cancelarPedido(Long pedidoId) {
        Pedido pedido = pedidoRepository.buscar(pedidoId);
        pedido.cancelarPedido();
        return pedidoRepository.salvar(pedido);
    }
}
