package org.questao.pedidos.pedido.intraestrutura;

import org.questao.pedidos.itemPedido.dominio.ItemPedido;
import org.questao.pedidos.itemPedido.dto.ItemPedidoResponse;
import org.questao.pedidos.itemPedido.infraestrutura.ItemPedidoEntity;
import org.questao.pedidos.itemPedido.infraestrutura.ItemPedidoMapper;
import org.questao.pedidos.pedido.dominio.Pedido;
import org.questao.pedidos.pedido.dto.PedidoEntregaResponseDTO;
import org.questao.pedidos.pedido.dto.PedidoRequest;
import org.questao.pedidos.pedido.dto.PedidoResponseDTO;

import java.util.List;

public final class PedidoMapper {

    private PedidoMapper() {
    }

    public static PedidoEntity toEntity(Pedido pedido) {

        PedidoEntity pedidoEntity = new PedidoEntity(
                pedido.getIdPedido(),
                pedido.getEndereco(),
                pedido.getUsuario(),
                pedido.getEmpresa(),
                pedido.getEntregador(),
                pedido.getStatus(),
                pedido.getDataPedido(),
                pedido.getValorCompra(),
                null
        );

        List<ItemPedidoEntity> itens = pedido.getItens().stream().map(item -> {
            ItemPedidoEntity itemEntity = ItemPedidoMapper.toEntity(item);itemEntity.setPedido(pedidoEntity);
            return itemEntity;
        }).toList();

        pedidoEntity.setItens(itens);

        return pedidoEntity;
    }

    public static Pedido toDomain(PedidoEntity entity) {
        List<ItemPedido> itens = entity.getItens().stream().map(ItemPedidoMapper::toDomain).toList();

        return Pedido.reconstituir(
                entity.getIdPedido(),
                entity.getEndereco(),
                entity.getUsuario(),
                entity.getEmpresa(),
                entity.getEntregador(),
                entity.getStatus(),
                entity.getDataPedido(),
                entity.getValorCompra(),
                itens
        );
    }

    public static PedidoResponseDTO produtoItemPedidoDTO(Pedido pedido, String mensagem) {
        return new PedidoResponseDTO(pedido.getIdPedido(), mensagem);
    }

    public static Pedido requestToDominio(PedidoRequest pedidoRequest) {
        List<ItemPedido> itens = pedidoRequest.produtos().stream().map(ItemPedidoMapper::requestToDominio).toList();

        return Pedido.iniciarPedido(pedidoRequest.enderecoId(),
                pedidoRequest.usuarioId(),
                pedidoRequest.empresaId(),
                pedidoRequest.valorCompra(),
                itens);
    }

    public static PedidoEntregaResponseDTO pedidoEntregaResponseDTO(Pedido pedido) {
        List<ItemPedidoResponse> list = pedido.getItens().stream().map(ItemPedidoMapper::itemPedidoResponseDTO).toList();

        return new PedidoEntregaResponseDTO(
                pedido.getIdPedido(),
                pedido.getUsuario(),
                pedido.getEndereco(),
                pedido.getValorCompra(),
                list,
                pedido.getDataPedido(),
                pedido.getStatus());
    }
}
