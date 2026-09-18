package org.questao.pedidos.pedido.service;

import org.questao.pedidos.pedido.dominio.Pedido;
import org.questao.pedidos.pedido.intraestrutura.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class AberturaPedidoService {

    private PedidoRepository pedidoRepository;

    public AberturaPedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido aberturaPedido(Long endereco, Long usuario, Long empresa) {
        Pedido pedido = Pedido.aberturaPedido(endereco, usuario, empresa);
        return pedidoRepository.salvar(pedido);
    }
}
