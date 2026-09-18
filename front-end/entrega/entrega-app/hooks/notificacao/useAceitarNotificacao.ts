import { useState } from "react";

import {
  aceitarSolicitacao,
} from "../../service/notificacao/notificacaoService";

export function useAceitarNotificacao() {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  async function aceitar(idSolicitacao: number) {
    setLoading(true);
    setError("");

    try {
      const pedido = await aceitarSolicitacao(idSolicitacao);
      return pedido;
    } catch (error) {
      setError("Erro ao aceitar solicitação.");
      return null;
    } finally {
      setLoading(false);
    }
  }

  return {
    aceitar,
    loading,
    error,
  };
}