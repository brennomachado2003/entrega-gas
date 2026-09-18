package org.questao.pedidos.pedido.service;

import org.questao.pedidos.pedido.dominio.Pedido;
import org.questao.pedidos.pedido.intraestrutura.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarPedidosPorUsuarioService {

    private final PedidoRepository pedidoRepository;

    public ListarPedidosPorUsuarioService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listarPedidosPorUsuario(Long usuarioId) {
        List<Pedido> pedidos = pedidoRepository.listarPorUsuario(usuarioId);
        return pedidos;
    }
}
