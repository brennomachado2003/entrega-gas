import { get, patch, post } from "./http";
import {HistoricoEntregadorRequestDTO, HistoricoRequestDTO,PedidoEntregaResponseDTO} from "../components/listaPedidos/types";
import {PedidoRequest, PedidoResponseDTO} from "../pages/pedido/types";

export function listaPedidos(request: HistoricoRequestDTO): Promise<PedidoEntregaResponseDTO[]> {
  return get(`/pedido/historico?usuarioId=${request.usuarioId}`);
}

export function cadastrarPedido( pedido: PedidoRequest): Promise<PedidoResponseDTO> {
  return post("/pedido", pedido);
}

export function cancelarPedido(pedidoId: number): Promise<PedidoResponseDTO> {
  return patch("/pedido/cancelarPedido", pedidoId);
}

export function listaPedidosEntregador(request: HistoricoEntregadorRequestDTO): Promise<PedidoEntregaResponseDTO[]> {
  return get(`/pedido/historico/entrega?entregadorId=${request.entregadorId}`);
}