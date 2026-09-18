package org.questao.pedidos.pedido.intraestrutura;

import org.questao.pedidos.pedido.dominio.Pedido;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PedidoRepositoryAdapter implements PedidoRepository {

    private final PedidoJPARepository pedidoJPARepository;

    public PedidoRepositoryAdapter(PedidoJPARepository pedidoJPARepository) {
        this.pedidoJPARepository = pedidoJPARepository;
    }

    @Override
    public List<Pedido> listar() {
        List<PedidoEntity> pedidoEntityList = pedidoJPARepository.findAll();
        return pedidoEntityList.stream().map(PedidoMapper::toDomain).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Pedido buscar(Long id) {
        PedidoEntity pedidoEntity = pedidoJPARepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + id));
        return PedidoMapper.toDomain(pedidoEntity);
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        PedidoEntity pedidoEntity = PedidoMapper.toEntity(pedido);
        return PedidoMapper.toDomain(pedidoJPARepository.save(pedidoEntity));
    }

    @Override
    public Pedido atualizar(Long id, Pedido dados) {
        Pedido pedido = buscar(id);
        pedido.atualizar(dados);
        return salvar(pedido);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Pedido> listarPorUsuario(Long usuarioId) {
        List<PedidoEntity> pedidoEntityList = pedidoJPARepository.findByUsuarioOrderByDataPedidoDesc(usuarioId);
        return pedidoEntityList.stream().map(PedidoMapper::toDomain).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Pedido> listarPorEntregador(Long entregadorId) {
        List<PedidoEntity> pedidoEntityList = pedidoJPARepository.findByEntregadorOrderByDataPedidoDesc(entregadorId);
        return pedidoEntityList.stream().map(PedidoMapper::toDomain).toList();
    }

}
