package org.questao.pedidos.itemPedido.dominio;


import lombok.Getter;
import lombok.Setter;
import org.questao.pedidos.pedido.intraestrutura.PedidoEntity;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemPedido {

    private final Long idPedidoProduto;
    private final PedidoEntity pedido;
    private final Long idProduto;
    private Integer quantidade;
    private ValorUnitario valorUnitario;
    private Desconto desconto;

    public ItemPedido(Long idPedidoProduto, PedidoEntity pedido, Long idProduto, Integer quantidade, ValorUnitario valorUnitario, Desconto desconto) {
        this.idPedidoProduto = idPedidoProduto;
        this.pedido = pedido;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.desconto = desconto;
    }

    public static ItemPedido reconstituir(Long idPedidoProduto, PedidoEntity pedido, Long idProduto, Integer quantidade, BigDecimal valorUnitario, BigDecimal desconto) {
        return new ItemPedido(idPedidoProduto, pedido, idProduto, quantidade, new ValorUnitario(valorUnitario), new Desconto(desconto));
    }

    public void atualizar(ItemPedido dados) {
        this.quantidade = dados.getQuantidade();
        this.valorUnitario = dados.getValorUnitario();
        this.desconto = dados.getDesconto();
    }
}
