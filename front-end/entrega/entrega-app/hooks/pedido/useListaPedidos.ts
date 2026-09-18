import { useCallback, useEffect, useState } from "react";

import {
  HistoricoRequestDTO,
  PedidoEntregaResponseDTO,
} from "../../components/listaPedidos/types";

import { listaPedidos } from "../../service/pedido/pedidosService";
import { obterUsuario } from "../../service/authStorage";

export function useListaPedidos() {
  const [pedidos, setPedidos] = useState<PedidoEntregaResponseDTO[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const carregarPedidos = useCallback(async () => {
    try {
      setLoading(true);
      setError("");

      const usuario = await obterUsuario();

      if (!usuario) {
        throw new Error("Usuário não encontrado");
      }

      const request: HistoricoRequestDTO = {
        usuarioId: usuario.id,
      };

      const response = await listaPedidos(request);

      setPedidos(response);
    } catch (e) {
      console.error("Erro ao carregar pedidos:", e);
      setError("Não foi possível carregar os pedidos.");
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    carregarPedidos();
  }, [carregarPedidos]);

  return {pedidos,loading,error,carregarPedidos,};
}