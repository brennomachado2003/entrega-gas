package org.questao.estoque.estoque.movimentacao.infraestrutura;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.questao.estoque.estoque.movimentacao.dominio.TipoMovimentacao;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movimentacao")
public class MovimentacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimentacao")
    private Long idMovimentacao;

    @Column(name = "id_pedido")
    private Long pedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimentacao")
    private TipoMovimentacao tipoMovimentacao;

    @Column(name = "data_hora", insertable = false, updatable = false)
    private LocalDateTime dataHora;

    @Column(name = "quantidade_botijao_cheio")
    private Integer quantidadeBotijaoCheio;

    @Column(name = "quantidade_botijao_vazio")
    private Integer quantidadeBotijaoVazio;

    @Column(name = "venda_botijao_completo")
    private Integer quantidadeBotijaoCompleto;

    @Column(name = "observarcao")
    private String observacao;

    @Column(name="id_operacao_entrega")
    public Long operacaoEntrega;
}