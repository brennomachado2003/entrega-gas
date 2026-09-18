import { get, post , conectarSSE} from "../http";
import {SolicitacaoEntregaResponseDTO} from "../../service/notificacao/type";

export function listarSolicitacoesPedido(
  pedidoId: number
): Promise<SolicitacaoEntregaResponseDTO[]> {
  return get(`/solicitacoes/pedido/${pedidoId}`);
}

export function notificarEntregador(
  idEntregador: number
): Promise<SolicitacaoEntregaResponseDTO[]> {
  return get(`/solicitacoes/notificar/entregador/${idEntregador}`);
}

export function expirarSolicitacao(
  idSolicitacao: number
): Promise<string> {
  return post(`/solicitacoes/${idSolicitacao}/expirar`);
}

export function recusarSolicitacao(
  idSolicitacao: number
): Promise<string> {
  return post(`/solicitacoes/${idSolicitacao}/recusar`);
}

export function aceitarSolicitacao(
  idSolicitacao: number
): Promise<string> {
  return post(`/solicitacoes/${idSolicitacao}/aceitar`);
}

export function solicitarEntregador(
  pedidoId: number
): Promise<string> {
  return post(`/solicitacoes/${pedidoId}/solicitarEntregador`);
}

export function conectarSolicitacoesSSE(idEntregador: number) {
  return conectarSSE(`/notificacoes/entregadores/${idEntregador}/stream`);
}