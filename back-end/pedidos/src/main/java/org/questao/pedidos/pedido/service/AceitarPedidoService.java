package org.questao.pedidos.pedido.service;

import org.questao.pedidos.pedido.dominio.Pedido;
import org.questao.pedidos.pedido.intraestrutura.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AceitarPedidoService {

    private final PedidoRepository pedidoRepository;

    public AceitarPedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido aceitarPedido(Long idPedido, Long idEntregador) {
        Pedido pedido = pedidoRepository.buscar(idPedido);
        pedido.aceitarPedido(idEntregador);
        return pedidoRepository.salvar(pedido);
    }
}
