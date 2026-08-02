package com.brenno.entrega.pedido.model;

import com.brenno.entrega.empresa.model.Empresa;
import com.brenno.entrega.entregador.model.Entregador;
import com.brenno.entrega.pedido.itemPedido.model.ItemPedido;
import com.brenno.entrega.pedido.statusPedido.model.StatusPedido;
import com.brenno.entrega.user.endereco.modal.Endereco;
import com.brenno.entrega.user.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido", schema = "entrega_gas")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;

    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "id_entregador")
    private Entregador entregador;

    @ManyToOne
    @JoinColumn(name = "id_status", nullable = false)
    private StatusPedido status;

    @Column(name = "data_pedido", insertable = false, updatable = false)
    private LocalDateTime dataPedido;

    @Column(name = "valor_compra")
    private BigDecimal valorCompra;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<ItemPedido> itens;

    public Pedido(Usuario usuario, Empresa empresa, StatusPedido status, Endereco endereco, BigDecimal valorCompra) {
        this.usuario = usuario;
        this.empresa = empresa;
        this.status = status;
        this.endereco = endereco;
        this.valorCompra = valorCompra;
    }
}