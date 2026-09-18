package org.questao.logistica.logistica.rastreamento.infraestrutura;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import org.locationtech.jts.geom.Point;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posicao_entregador")
public class PosicaoEntregadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_posicao_entregador")
    private Long idPosicaoEntregador;

    @Column(name = "id_entregador", nullable = false)
    private Long entregador;

    @Column(name = "localizacao", columnDefinition = "geometry(Point,4326)")
    private Point localizacao;

    @Column(name = "ultima_atualizacao")
    private LocalDateTime ultimaAtualizacao;
}