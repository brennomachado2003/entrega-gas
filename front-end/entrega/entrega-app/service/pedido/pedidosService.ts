import { conectarSSE, get, patch, post } from "../http";

import {AceitarPedidoRequestDTO, HistoricoEntregadorRequestDTO, HistoricoRequestDTO, PedidoEntregaResponseDTO} from "../../components/listaPedidos/types";

import {PedidoRequest,PedidoResponseDTO} from "../../pages/usuario/fazerPedido/types";

export function listaPedidos(request: HistoricoRequestDTO): Promise<PedidoEntregaResponseDTO[]> {
  return get(`/pedido/historico?usuarioId=${request.usuarioId}`);
}

export function cadastrarPedido(pedido: PedidoRequest): Promise<PedidoResponseDTO> {
  return post("/pedido", pedido);
}

export function cancelarPedido(pedidoId: number): Promise<PedidoResponseDTO> {
  return patch("/pedido/cancelarPedido", pedidoId);
}

export function listaPedidosEntregador( request: HistoricoEntregadorRequestDTO): Promise<PedidoEntregaResponseDTO[]> {
  return get(
    `/pedido/historico/entrega?entregadorId=${request.entregadorId}`
  );
}

export function entregadorDefinido(request: AceitarPedidoRequestDTO): Promise<PedidoResponseDTO> {
  return patch("/pedido/entregadorDefinido", request);
}

export function conectarPedidosSSE(idPedido: number) {
  return conectarSSE(`/pedidoAceito/${idPedido}/stream`);
}