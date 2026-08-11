package com.brenno.entrega.pedido.itemPedido.model;

import com.brenno.entrega.pedido.model.Pedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido_produto", schema = "entrega_gas")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido_produto")
    private Integer idPedidoProduto;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    @ToString.Exclude
    private Pedido pedido;

    @Column(name = "id_produto", nullable = false)
    private Integer idProduto;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "valor_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorUnitario;

    @Column(name = "desconto", nullable = false, precision = 10, scale = 2)
    private BigDecimal desconto;

    public ItemPedido(Pedido pedido, Integer idProduto,  Integer quantidade, BigDecimal valorUnitario, BigDecimal desconto) {
        this.pedido = pedido;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.desconto = desconto;
    }
}