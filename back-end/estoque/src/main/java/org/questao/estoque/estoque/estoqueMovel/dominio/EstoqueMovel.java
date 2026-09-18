package org.questao.estoque.estoque.estoqueMovel.dominio;

import lombok.Getter;
import lombok.Setter;
import org.questao.estoque.estoque.movimentacao.dominio.Movimentacao;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class EstoqueMovel {

    private Long idOperacaoEntrega;
    private Long entregador;
    private LocalDateTime dataHora;
    private List<Movimentacao> movimentacoes;

    public EstoqueMovel(Long idOperacaoEntrega, Long entregador, LocalDateTime dataHora, List<Movimentacao> movimentacoes) {
        this.idOperacaoEntrega = idOperacaoEntrega;
        this.entregador = entregador;
        this.dataHora = dataHora;
        this.movimentacoes = movimentacoes;
    }
    public EstoqueMovel(Long entregador) {
        this.entregador = entregador;
        this.dataHora = LocalDateTime.now();
    }

    public static EstoqueMovel reconstituir(
            Long idOperacaoEntrega,
            Long entregador,
            LocalDateTime dataHora,
            List<Movimentacao> movimentacoes
    ) {
        return new EstoqueMovel(idOperacaoEntrega, entregador, dataHora, movimentacoes);
    }

    public static EstoqueMovel criar(Long entregador){
        return new EstoqueMovel(entregador);
    }

}
