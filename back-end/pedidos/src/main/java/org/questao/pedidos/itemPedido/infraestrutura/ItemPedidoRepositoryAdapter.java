package org.questao.pedidos.itemPedido.infraestrutura;

import org.questao.pedidos.itemPedido.dominio.ItemPedido;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemPedidoRepositoryAdapter implements ItemPedidoRepository {

    private final ItemPedidoJPARepository itemPedidoJPARepository;

    public ItemPedidoRepositoryAdapter(ItemPedidoJPARepository itemPedidoJPARepository) {
        this.itemPedidoJPARepository = itemPedidoJPARepository;
    }

    @Override
    public List<ItemPedido> listar() {
        List<ItemPedidoEntity> itemPedidoEntityList = itemPedidoJPARepository.findAll();
        return itemPedidoEntityList.stream().map(ItemPedidoMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public ItemPedido buscar(Long id) {
        ItemPedidoEntity itemPedido = itemPedidoJPARepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado: " + id));
        return ItemPedidoMapper.toDomain(itemPedido);
    }

    @Override
    public ItemPedido salvar(ItemPedido itemPedido) {
        ItemPedidoEntity itemPedidoEntity = ItemPedidoMapper.toEntity(itemPedido);
        return ItemPedidoMapper.toDomain(itemPedidoJPARepository.save(itemPedidoEntity));
    }

    @Override
    public ItemPedido atualizar(Long id, ItemPedido dados) {
        ItemPedido itemPedido = buscar(id);
        itemPedido.atualizar(dados);
        return salvar(itemPedido);
    }

    @Override
    public void excluir(Long id) {
        ItemPedido itemPedido = buscar(id);
        itemPedidoJPARepository.delete(ItemPedidoMapper.toEntity(itemPedido));
    }
}

