package org.questao.estoque.estoque.estoqueMovel.infraestrutura;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.questao.estoque.estoque.movimentacao.infraestrutura.MovimentacaoEntity;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "operacao_entrega")
public class EstoqueMovelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operacao_entrega")
    private Long idOperacaoEntrega;

    @Column(name = "id_entregador", nullable = false)
    private Long entregador;

    @Column(name = "data_hora", insertable = false, updatable = false)
    private LocalDateTime dataHora;

    @OneToMany(mappedBy = "operacaoEntrega")
    public List<MovimentacaoEntity> movimentacoes;
}
