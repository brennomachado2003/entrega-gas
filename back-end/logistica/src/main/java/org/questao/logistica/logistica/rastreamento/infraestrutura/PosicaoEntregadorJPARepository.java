package org.questao.logistica.logistica.rastreamento.infraestrutura;

import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PosicaoEntregadorJPARepository extends JpaRepository<PosicaoEntregadorEntity, Long> {
    PosicaoEntregadorEntity findByEntregador(Long entregador);

    @Query(value = """
    SELECT *
    FROM posicao_entregador pe
    WHERE ST_DWithin(
        pe.localizacao::geography,
        CAST(:localizacao AS geography),
        :raio
    )
    """, nativeQuery = true)
    List<PosicaoEntregadorEntity> findEntregadoresProximos(
            @Param("localizacao") Point localizacao,
            @Param("raio") double raio
    );

}
