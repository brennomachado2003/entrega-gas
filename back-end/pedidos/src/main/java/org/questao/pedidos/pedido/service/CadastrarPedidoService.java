package org.questao.pedidos.pedido.service;

import org.questao.pedidos.pedido.dominio.Pedido;
import org.questao.pedidos.pedido.dominio.StatusPedido;
import org.questao.pedidos.pedido.dto.PedidoRequest;
import org.questao.pedidos.pedido.intraestrutura.PedidoMapper;
import org.questao.pedidos.pedido.intraestrutura.PedidoRepository;
import org.questao.pedidos.publicador.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CadastrarPedidoService {

    private final PedidoRepository pedidoRepository;
    private final DomainEventPublisher domainEventPublisher;

    public CadastrarPedidoService(PedidoRepository pedidoRepository, @Qualifier("kafkaDomainEventsPublisher") DomainEventPublisher domainEventPublisher) {
        this.pedidoRepository = pedidoRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    public Pedido criarPedido(PedidoRequest request) {
        Pedido pedido = PedidoMapper.requestToDominio(request);
        pedido.setStatus(StatusPedido.PENDENTE);
        pedido.setDataPedido(LocalDateTime.now());
        Pedido salvo = pedidoRepository.salvar(pedido);
        salvo.pedidoCriado();
        domainEventPublisher.publicar(salvo.getEvents());
        salvo.limparEvents();
        return salvo;
    }
}
