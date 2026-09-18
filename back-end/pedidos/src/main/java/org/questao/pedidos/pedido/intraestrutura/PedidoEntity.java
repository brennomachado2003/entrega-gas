package org.questao.pedidos.pedido.intraestrutura;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.questao.pedidos.itemPedido.infraestrutura.ItemPedidoEntity;
import org.questao.pedidos.pedido.dominio.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long idPedido;

    @Column(name = "id_endereco")
    private Long endereco;

    @Column(name = "id_usuario", nullable = false)
    private Long usuario;

    @Column(name = "id_empresa", nullable = false)
    private Long empresa;

    @Column(name = "id_entregador")
    private Long entregador;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "id_status", nullable = false)
    private StatusPedido status;

    @Column(name = "data_pedido", insertable = false, updatable = false)
    private LocalDateTime dataPedido;

    @Column(name = "valor_compra")
    private BigDecimal valorCompra;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<ItemPedidoEntity> itens;

    public PedidoEntity(Long usuario, Long empresa, StatusPedido status, Long endereco, BigDecimal valorCompra) {
        this.usuario = usuario;
        this.empresa = empresa;
        this.status = status;
        this.endereco = endereco;
        this.valorCompra = valorCompra;
    }
}