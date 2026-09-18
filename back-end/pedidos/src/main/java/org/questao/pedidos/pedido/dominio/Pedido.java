package org.questao.pedidos.pedido.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.questao.pedidos.itemPedido.dominio.ItemPedido;
import org.questao.pedidos.pedido.intraestrutura.events.PedidoCriado;
import org.questao.pedidos.publicador.AggregateRoot;
import org.questao.pedidos.publicador.DomainEvent;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido extends AggregateRoot {

    private Long idPedido;
    private Long endereco;
    private Long usuario;
    private Long empresa;
    private Long entregador;
    private StatusPedido status;
    private LocalDateTime dataPedido;
    private BigDecimal valorCompra;
    private List<ItemPedido> itens;

    public Pedido(Long endereco, Long usuario, Long empresa, StatusPedido status, LocalDateTime dataPedido, BigDecimal valorCompra, List<ItemPedido> itens) {
        this.endereco = endereco;
        this.usuario = usuario;
        this.empresa = empresa;
        this.status = status;
        this.dataPedido = dataPedido;
        this.valorCompra = valorCompra;
        this.itens = itens;
    }

    public Pedido(Long endereco, Long usuario, Long empresa, StatusPedido status) {
        this.endereco = endereco;
        this.usuario = usuario;
        this.empresa = empresa;
        this.status = status;
    }

    public static Pedido reconstituir(Long idPedido, Long endereco, Long usuario, Long empresa, Long entregador, StatusPedido status, LocalDateTime dataPedido, BigDecimal valorCompra, List<ItemPedido> itens) {
        return new Pedido(idPedido, endereco, usuario, empresa, entregador, status, dataPedido, valorCompra, itens);
    }

    public static Pedido iniciarPedido(Long enderecoId, Long usuario, Long empresaId, BigDecimal valorCompra, List<ItemPedido> produtos) {
        return new Pedido(enderecoId, usuario, empresaId, StatusPedido.PENDENTE, LocalDateTime.now(), valorCompra, produtos);
    }

    public void atualizar(Pedido dados) {
        this.endereco = dados.getEndereco();
        this.entregador = dados.getEntregador();
        this.status = dados.getStatus();
        this.valorCompra = dados.getValorCompra();
        this.itens = dados.getItens();
    }

    public static Pedido aberturaPedido(Long endereco, Long usuario, Long empresa) {
        return new Pedido(endereco, usuario, empresa, StatusPedido.PENDENTE);
    }

    public void cancelarPedido() {
        this.status = StatusPedido.CANCELADO;
    }

    public void aceitarPedido(Long entregadorId) {
        if (status != StatusPedido.PENDENTE) throw new IllegalStateException("Pedido não está pendente");
        this.entregador = entregadorId;
        this.status = StatusPedido.EM_ROTA;
    }

    public void pedidoCriado(){
        register(new PedidoCriado(idPedido, endereco));
    }
}
