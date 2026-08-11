package com.brenno.entrega.pedido.itemPedido.service;

import com.brenno.entrega.pedido.itemPedido.dto.ItemPedidoRequest;
import com.brenno.entrega.pedido.itemPedido.dto.ProdutoItemPedidoDTO;
import com.brenno.entrega.pedido.model.Pedido;
import com.brenno.entrega.pedido.itemPedido.model.ItemPedido;
import com.brenno.entrega.produto.api.ProdutoApi;
import com.brenno.entrega.produto.dto.ProdutoResponseDTO;
import com.brenno.entrega.pedido.itemPedido.repository.ItemPedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoService {
    private final ItemPedidoRepository pedidoProdutoRepository;
    private final ProdutoApi produtoApi;

    public ItemPedidoService(ItemPedidoRepository pedidoProdutoRepository, ProdutoApi produtoApi) {
        this.pedidoProdutoRepository = pedidoProdutoRepository;
        this.produtoApi = produtoApi;
    }

    public ItemPedido save(ItemPedido pedidoProduto) {
        return pedidoProdutoRepository.save(pedidoProduto);
    }

    public List<ItemPedido> findAll() {
        return pedidoProdutoRepository.findAll();
    }

    public Optional<ItemPedido> findById(Integer id) {
        return pedidoProdutoRepository.findById(id);
    }

    public void delete(ItemPedido pedidoProduto) {
        pedidoProdutoRepository.delete(pedidoProduto);
    }

    public void adicionandoProdutoAoPedido(Pedido pedido, List<ItemPedidoRequest> produtos) {
        produtos.forEach(item -> {
            ProdutoResponseDTO produto = produtoApi.buscar(item.getProdutoId());
            save(new ItemPedido(pedido, item.getProdutoId(), item.getQuantidade(), produto.getValor(), item.getDesconto()));
        });
    }

    public ProdutoItemPedidoDTO itemParaPedidoEntregaResponseDTO(ItemPedido item){
        ProdutoResponseDTO produto = produtoApi.buscar(item.getIdProduto());

        return new ProdutoItemPedidoDTO(
                    produto.getNome(),
                    item.getQuantidade(),
                    item.getValorUnitario(),
                    item.getDesconto()
            );
    }


}
