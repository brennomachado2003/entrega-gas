package com.brenno.entrega.controleEstoque.movimentacao.service;

import com.brenno.entrega.controleEstoque.movimentacao.dto.MovimentacaoCargaInicialRuequestDTO;
import com.brenno.entrega.controleEstoque.movimentacao.dto.MovimentacaoReabastecerRequestDTO;
import com.brenno.entrega.controleEstoque.movimentacao.dto.MovimentacaoVendaRequestDTO;
import com.brenno.entrega.controleEstoque.movimentacao.dto.MovimentcaoResponseDTO;
import com.brenno.entrega.controleEstoque.movimentacao.model.Movimentacao;
import com.brenno.entrega.controleEstoque.movimentacao.repository.MovimentacaoRepository;
import com.brenno.entrega.controleEstoque.movimentacao.tipoMovimentacao.TipoMovimentacao;
import com.brenno.entrega.pedido.itemPedido.model.ItemPedido;
import com.brenno.entrega.pedido.model.Pedido;
import com.brenno.entrega.pedido.service.PedidoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoService {
    private final MovimentacaoRepository movimentacaoRepository;
    private final PedidoService pedidoService;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, PedidoService pedidoService) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.pedidoService = pedidoService;
    }

    public MovimentcaoResponseDTO cargaInicial(MovimentacaoCargaInicialRuequestDTO movimentacaoCargaInicialRuequestDTO) {
        Movimentacao movimentacao = MovimentacaoCargaInicialRuequestDTOParaMovimentacao(movimentacaoCargaInicialRuequestDTO);
        return MovimentacaoParaMovimentcaoResponseDTO(movimentacaoRepository.save(movimentacao));
    }
    public MovimentcaoResponseDTO venda(MovimentacaoVendaRequestDTO movimentacaoVendaRequestDTO) {
        MovimentacaoVendaRequestDTO movimentacaoVenda = ComputarMovimentacaoVenda(movimentacaoVendaRequestDTO);
        Movimentacao movimentacao = MovimentacaoVendaRequestDTOParaMovimentacao(movimentacaoVenda);
        return MovimentacaoParaMovimentcaoResponseDTO(movimentacaoRepository.save(movimentacao));
    }
    public MovimentcaoResponseDTO reabastecer(MovimentacaoReabastecerRequestDTO movimentacaoReabastecerRequestDTO) {
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setOperacaoEntrega(movimentacaoReabastecerRequestDTO.getOperacaoEntrega());
        movimentacao.setDataHora(LocalDateTime.now());
        movimentacao.setTipoMovimentacao(TipoMovimentacao.REABASTECER);
        movimentacao.setQuantidadeBotijaoCheio(movimentacaoReabastecerRequestDTO.getQuantidadeBotijaoCheio());
        movimentacao.setQuantidadeBotijaoVazio(-movimentacaoReabastecerRequestDTO.getQuantidadeBotijaoVazio());
        return MovimentacaoParaMovimentcaoResponseDTO(movimentacaoRepository.save(movimentacao));
    }

    public Movimentacao MovimentacaoVendaRequestDTOParaMovimentacao(MovimentacaoVendaRequestDTO movimentacaoVendaRequestDTO){
        Pedido pedido = pedidoService.findById(movimentacaoVendaRequestDTO.getIdPedido());
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setOperacaoEntrega(movimentacaoVendaRequestDTO.getOperacaoEntrega());
        movimentacao.setPedido(pedido);
        movimentacao.setTipoMovimentacao(movimentacaoVendaRequestDTO.getTipoMovimentacao());
        movimentacao.setDataHora(movimentacaoVendaRequestDTO.getDataEntrega());
        movimentacao.setQuantidadeBotijaoCheio(movimentacaoVendaRequestDTO.getQuantidadeBotijaoCheio());
        movimentacao.setQuantidadeBotijaoVazio(movimentacaoVendaRequestDTO.getQuantidadeBotijaoVazio());
        movimentacao.setQuantidadeBotijaoCompleto(movimentacao.getQuantidadeBotijaoCompleto());
        movimentacao.setOperacaoEntrega(movimentacaoVendaRequestDTO.getOperacaoEntrega());
        return movimentacao;

    }
    public MovimentcaoResponseDTO MovimentacaoParaMovimentcaoResponseDTO(Movimentacao movimentacao) {
        MovimentcaoResponseDTO movimentacaoResponseDTO = new MovimentcaoResponseDTO();
        movimentacaoResponseDTO.setIdEntregador(movimentacao.getOperacaoEntrega().getEntregador().getIdEntregador());
        movimentacaoResponseDTO.setIdPedido(movimentacao.getPedido().getIdPedido());
        movimentacaoResponseDTO.setDataEntrega(movimentacao.getDataHora());
        movimentacaoResponseDTO.setQuantidadeBotijaoCheio(movimentacao.getQuantidadeBotijaoCheio());
        movimentacaoResponseDTO.setQuantidadeBotijaoVazio(movimentacao.getQuantidadeBotijaoVazio());
        movimentacaoResponseDTO.setQuantidadeBotijaoCompleto(movimentacao.getQuantidadeBotijaoCompleto());
        return movimentacaoResponseDTO;
    }
    public Movimentacao MovimentacaoCargaInicialRuequestDTOParaMovimentacao (MovimentacaoCargaInicialRuequestDTO movimentacaoCargaInicialRuequestDTO){
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setOperacaoEntrega(movimentacaoCargaInicialRuequestDTO.getOperacaoEntrega());
        movimentacao.setTipoMovimentacao(TipoMovimentacao.CARGA_INICIAL);
        movimentacao.setDataHora(LocalDateTime.now());
        movimentacao.setQuantidadeBotijaoCheio(movimentacaoCargaInicialRuequestDTO.getQuantidadeBotijaoCheio());
        return movimentacao;
    }
    public MovimentacaoVendaRequestDTO ComputarMovimentacaoVenda(MovimentacaoVendaRequestDTO movimentacaoVendaRequestDTO){
        Pedido pedido = pedidoService.findById(movimentacaoVendaRequestDTO.getIdPedido());
        List<ItemPedido> itens = pedido.getItens();
        itens.forEach(item -> {
            if(item.getIdProduto() == 1){
                movimentacaoVendaRequestDTO.setQuantidadeBotijaoCheio(movimentacaoVendaRequestDTO.getQuantidadeBotijaoCheio() - item.getQuantidade());
                movimentacaoVendaRequestDTO.setQuantidadeBotijaoVazio(movimentacaoVendaRequestDTO.getQuantidadeBotijaoVazio() + item.getQuantidade());
            }
            else if(item.getIdProduto() == 2){
                movimentacaoVendaRequestDTO.setQuantidadeBotijaoCheio(movimentacaoVendaRequestDTO.getQuantidadeBotijaoCheio() - item.getQuantidade());
                movimentacaoVendaRequestDTO.setQuantidadeBotijaoCompleto(movimentacaoVendaRequestDTO.getQuantidadeBotijaoCompleto() + item.getQuantidade());
            }
        });
        movimentacaoVendaRequestDTO.setDataEntrega(LocalDateTime.now());
        movimentacaoVendaRequestDTO.setTipoMovimentacao(TipoMovimentacao.VENDA);
        return movimentacaoVendaRequestDTO;
    }
    public Movimentacao save(Movimentacao movimentacao) {
        return movimentacaoRepository.save(movimentacao);
    }
    public List<Movimentacao> findAll() {
        return movimentacaoRepository.findAll();
    }
    public Optional<Movimentacao> findById(Integer id) {
        return movimentacaoRepository.findById(id);
    }
    public void delete(Movimentacao movimentacao) {
        movimentacaoRepository.delete(movimentacao);
    }
}
