package org.questao.pedidos.itemPedido.infraestrutura;

import org.questao.pedidos.itemPedido.dominio.ItemPedido;
import org.questao.pedidos.itemPedido.dto.ItemPedidoRequest;
import org.questao.pedidos.itemPedido.dto.ItemPedidoResponse;
import org.questao.pedidos.itemPedido.dto.ProdutoItemPedidoDTO;

public final class ItemPedidoMapper {

    private ItemPedidoMapper() {
    }

    public static ItemPedidoEntity toEntity(ItemPedido itemPedido) {

        return new ItemPedidoEntity(
                itemPedido.getIdPedidoProduto(),
                itemPedido.getPedido(),
                itemPedido.getIdProduto(),
                itemPedido.getQuantidade(),
                itemPedido.getValorUnitario().valor(),
                itemPedido.getDesconto().valor()
        );
    }

    public static ItemPedido toDomain(ItemPedidoEntity entity) {

        return ItemPedido.reconstituir(
                entity.getIdPedidoProduto(),
                entity.getPedido(),
                entity.getIdProduto(),
                entity.getQuantidade(),
                entity.getValorUnitario(),
                entity.getDesconto()
        );
    }

    public static ProdutoItemPedidoDTO produtoItemPedidoDTO(ItemPedido itemPedido) {
        return new ProdutoItemPedidoDTO(itemPedido.getIdProduto(), itemPedido.getQuantidade(), itemPedido.getValorUnitario().valor(), itemPedido.getDesconto().valor());
    }

    public static ItemPedido requestToDominio(ItemPedidoRequest request) {
        return ItemPedido.reconstituir(
                null,
                null,
                request.produtoId(),
                request.quantidade(),
                request.preco(),
                request.desconto()
        );
    }

    public static ItemPedidoResponse itemPedidoResponseDTO(ItemPedido itemPedido) {
        return new ItemPedidoResponse(
                itemPedido.getIdProduto(),
                itemPedido.getQuantidade(),
                itemPedido.getValorUnitario().valor(),
                itemPedido.getDesconto().valor()
        );
    }
}
