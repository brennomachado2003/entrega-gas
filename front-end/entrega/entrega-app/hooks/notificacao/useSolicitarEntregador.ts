import { useState } from "react";
import { solicitarEntregador } from "../../service/notificacaoService";

export function useSolicitarEntregador() {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  async function solicitar(pedidoId: number) {
    setLoading(true);
    setError("");

    try {
      await solicitarEntregador(pedidoId);
      return true;
    } catch {
      setError("Erro ao solicitar entregador.");
      return false;
    } finally {
      setLoading(false);
    }
  }

  return {
    solicitar,
    loading,
    error,
  };
}