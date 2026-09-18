import { useState } from "react";

import { expirarSolicitacao } from "../../service/notificacao/notificacaoService";

export function useExpirarNotificacao() {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  async function expirar(idSolicitacao: number) {
    setLoading(true);
    setError("");

    try {
      await expirarSolicitacao(idSolicitacao);
      return true;
    } catch (error) {
      setError("Erro ao expirar solicitação.");
      return false;
    } finally {
      setLoading(false);
    }
  }

  return {
    expirar,
    loading,
    error,
  };
}