import { get } from "../http";
import { ProdutoPedidoDTO } from "../../components/listProdutos/types";

export function listarProdutos(): Promise<ProdutoPedidoDTO[]> {
  return get("/produtos");
}

export function buscarProdutos(id: number): Promise<ProdutoPedidoDTO> {
  return get(`/produtos/${id}`);
}