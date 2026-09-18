import { useState } from "react";

import { recusarSolicitacao } from "../../service/notificacao/notificacaoService";

export function useRecusarNotificacao() {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  async function recusar(idSolicitacao: number) {
    setLoading(true);
    setError("");

    try {
      await recusarSolicitacao(idSolicitacao);
      return true;
    } catch (error) {
      setError("Erro ao recusar solicitação.");
      return false;
    } finally {
      setLoading(false);
    }
  }

  return {
    recusar,
    loading,
    error,
  };
}