package org.questao.estoque.estoque.movimentacao.dominio;

import lombok.Getter;
import lombok.Setter;
import org.questao.estoque.estoque.movimentacao.dto.*;
import org.questao.estoque.estoque.movimentacao.infraestrutura.MovimentacaoEntity;

import java.time.LocalDateTime;

@Getter
@Setter
public class Movimentacao {

    private final Long idMovimentacao;
    private final Long pedido;
    private TipoMovimentacao tipoMovimentacao;
    private final LocalDateTime dataHora;
    private Integer quantidadeBotijaoCheio;
    private Integer quantidadeBotijaoVazio;
    private Integer quantidadeBotijaoCompleto;
    private String observacao;
    private final Long operacaoEntrega;

    private Movimentacao(
            Long idMovimentacao,
            Long pedido,
            TipoMovimentacao tipoMovimentacao,
            LocalDateTime dataHora,
            Integer quantidadeBotijaoCheio,
            Integer quantidadeBotijaoVazio,
            Integer quantidadeBotijaoCompleto,
            String observacao,
            Long operacaoEntrega
    ) {
        this.idMovimentacao = idMovimentacao;
        this.pedido = pedido;
        this.tipoMovimentacao = tipoMovimentacao;
        this.dataHora = dataHora;
        this.quantidadeBotijaoCheio = quantidadeBotijaoCheio;
        this.quantidadeBotijaoVazio = quantidadeBotijaoVazio;
        this.quantidadeBotijaoCompleto = quantidadeBotijaoCompleto;
        this.observacao = observacao;
        this.operacaoEntrega = operacaoEntrega;
    }

    public static Movimentacao reconstituir(
            Long idMovimentacao,
            Long pedido,
            TipoMovimentacao tipoMovimentacao,
            LocalDateTime dataHora,
            Integer quantidadeBotijaoCheio,
            Integer quantidadeBotijaoVazio,
            Integer quantidadeBotijaoCompleto,
            String observacao,
            Long operacaoEntrega
    ) {
        return new Movimentacao(
                idMovimentacao,
                pedido,
                tipoMovimentacao,
                dataHora,
                quantidadeBotijaoCheio,
                quantidadeBotijaoVazio,
                quantidadeBotijaoCompleto,
                observacao,
                operacaoEntrega
        );
    }

    public static Movimentacao criar(
            Long pedido,
            TipoMovimentacao tipoMovimentacao,
            Integer quantidadeBotijaoCheio,
            Integer quantidadeBotijaoVazio,
            Integer quantidadeBotijaoCompleto,
            String observacao,
            Long operacaoEntrega
    ) {
        return new Movimentacao(
                null,
                pedido,
                tipoMovimentacao,
                LocalDateTime.now(),
                quantidadeBotijaoCheio,
                quantidadeBotijaoVazio,
                quantidadeBotijaoCompleto,
                observacao,
                operacaoEntrega
        );
    }

    public void atualizar(Movimentacao dados) {
        this.tipoMovimentacao = dados.getTipoMovimentacao();
        this.quantidadeBotijaoCheio = dados.getQuantidadeBotijaoCheio();
        this.quantidadeBotijaoVazio = dados.getQuantidadeBotijaoVazio();
        this.quantidadeBotijaoCompleto = dados.getQuantidadeBotijaoCompleto();
        this.observacao = dados.getObservacao();
    }

    public static Movimentacao cargaInicial(int quantidadeBotijaoCheio,
                                            String observacao,
                                            Long operacaoEntrega) {
        Movimentacao movimentacao = new Movimentacao(null, null, TipoMovimentacao.CARGA_INICIAL, LocalDateTime.now(), quantidadeBotijaoCheio, 0, 0,  observacao, operacaoEntrega);
        return movimentacao;
    }

    public static Movimentacao venda(Long idPedido, Integer quantidadeBotijaoCheio, Integer quantidadeBotijaoVazio, Integer quantidadeBotijaoCompleto, String observacao, Long operacaoEntrega) {
        Movimentacao movimentacao = new Movimentacao(null, idPedido,
                TipoMovimentacao.VENDA, LocalDateTime.now(), quantidadeBotijaoCheio,
                quantidadeBotijaoVazio, quantidadeBotijaoCompleto, observacao, operacaoEntrega);
        return movimentacao;
    }

    public static Movimentacao reabastecer(Integer quantidadeBotijaoCheio, Integer quantidadeBotijaoVazio, Long operacaoEntrega) {
        return new Movimentacao(null, null,
                TipoMovimentacao.REABASTECER,
                LocalDateTime.now(),
                quantidadeBotijaoCheio,
                quantidadeBotijaoVazio,
                0,
                null,
                operacaoEntrega);
    }




}
