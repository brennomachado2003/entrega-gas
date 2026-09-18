package org.questao.pedidos.pedido.service;

import org.questao.pedidos.pedido.dominio.Pedido;
import org.questao.pedidos.pedido.intraestrutura.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarPedidoService {

    private PedidoRepository pedidoJPARepository;

    public BuscarPedidoService(PedidoRepository pedidoJPARepository) {
        this.pedidoJPARepository = pedidoJPARepository;
    }

    public Pedido busacarPedido(Long id){
        return pedidoJPARepository.buscar(id);
    }
}
