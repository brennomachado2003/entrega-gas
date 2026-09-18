package org.questao.pedidos.pedido.service;

import org.questao.pedidos.pedido.dominio.Pedido;
import org.questao.pedidos.pedido.dominio.StatusPedido;
import org.questao.pedidos.pedido.dto.PedidoRequest;
import org.questao.pedidos.pedido.dto.PedidoResponseDTO;
import org.questao.pedidos.exception.PedidoJaAceitoException;
import org.questao.pedidos.itemPedido.service.ItemPedidoService;
import org.questao.pedidos.pedido.intraestrutura.PedidoEntity;
import org.questao.pedidos.pedido.intraestrutura.PedidoJPARepository;
import org.questao.pedidos.pedido.intraestrutura.PedidoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {
    private final PedidoJPARepository pedidoRepository;
    private final ItemPedidoService itemPedidoService;

    public PedidoService(PedidoJPARepository pedidoRepository, ItemPedidoService itemPedidoService) {
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoService = itemPedidoService;

    }


}
