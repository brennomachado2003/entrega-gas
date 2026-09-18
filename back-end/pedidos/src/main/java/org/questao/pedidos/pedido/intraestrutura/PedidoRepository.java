package org.questao.pedidos.pedido.intraestrutura;

import org.questao.pedidos.pedido.dominio.Pedido;

import java.util.List;

public interface PedidoRepository {
     List<Pedido> listar();
     Pedido buscar(Long id);
     Pedido salvar(Pedido pedido);
     Pedido atualizar(Long id, Pedido dados);
     List<Pedido> listarPorUsuario(Long usuarioId);
     List<Pedido> listarPorEntregador(Long entregadorId);
}
