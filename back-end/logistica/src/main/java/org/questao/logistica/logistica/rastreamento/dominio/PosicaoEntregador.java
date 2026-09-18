package org.questao.logistica.logistica.rastreamento.dominio;

import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Getter
@Setter
public class PosicaoEntregador {

    private final Long idPosicaoEntregador;
    private Long idEntregador;
    private Point localizacao;
    private LocalDateTime ultimaAtualizacao;

    public PosicaoEntregador(Long idPosicaoEntregador, Long entregador, Point localizacao, LocalDateTime ultimaAtualizacao) {
        this.idPosicaoEntregador = idPosicaoEntregador;
        this.idEntregador = entregador;
        this.localizacao = localizacao;
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public static PosicaoEntregador reconstituir(Long idPosicaoEntregador, Long entregador, Point localizacao,LocalDateTime ultimaAtualizacao) {
        return new PosicaoEntregador(idPosicaoEntregador, entregador, localizacao, ultimaAtualizacao);
    }

    public void atualizar(Point localizacao) {
        this.localizacao = localizacao;
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    public static PosicaoEntregador criarPosicaoEntregador(Long idEntregador) {
        return new PosicaoEntregador(null, idEntregador, null, LocalDateTime.now());
    }
}
