package org.questao.pedidos.pedido.service;

import org.questao.pedidos.pedido.dominio.Pedido;
import org.questao.pedidos.pedido.intraestrutura.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarPedidosPorEntregadorService {

    private final PedidoRepository pedidoRepository;

    public ListarPedidosPorEntregadorService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listaPedidosPorEntregador(Long entregadorId) {
        return pedidoRepository.listarPorEntregador(entregadorId);
    }
}
