import { useEffect, useState } from "react";

import { conectarSolicitacoesSSE } from "../../service/notificacao/notificacaoService";
import { SolicitacaoEntregaAceitaEvent, SolicitacaoEntregaCriadaEvent } from "../../service/notificacao/type";
import { obterUsuario } from "../../service/authStorage";

export function useNotificarEntregador() {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const [solicitacoes, setSolicitacoes] = useState<
    SolicitacaoEntregaCriadaEvent[]
  >([]);

  function removerSolicitacao(idSolicitacao: number) {
    setSolicitacoes((atual) =>
      atual.filter(
        (solicitacao) =>
          solicitacao.idSolicitacao !== idSolicitacao
      )
    );
  }

  function removerSolicitacaoPorPedido(idPedido: number) {
    setSolicitacoes((atual) =>
      atual.filter(
        (solicitacao) => solicitacao.idPedido !== idPedido
      )
    );
  }

  useEffect(() => {
    let eventSource: EventSource | null = null;

    async function conectar() {
      try {
        setLoading(true);
        setError("");

        const usuario = await obterUsuario();

        if (!usuario) {
          setError("Usuário não encontrado.");
          return;
        }
        eventSource = conectarSolicitacoesSSE(usuario.id);
        eventSource.onerror = (erro) => {
          console.error("❌ ERRO SSE:", erro);
          setError(
            "Erro na conexão com as notificações."
          );
        };

        eventSource.addEventListener(
          "solicitacao-entrega",
          (event) => {
            const solicitacao: SolicitacaoEntregaCriadaEvent = JSON.parse(event.data);
            setSolicitacoes((atual) => [
              ...atual,
              solicitacao,
            ]);
          }
        );

        eventSource.addEventListener(
          "solicitacao-aceita",
          (event) => {
            const solicitacaoAceita: SolicitacaoEntregaAceitaEvent = JSON.parse(event.data);
            removerSolicitacaoPorPedido(
              solicitacaoAceita.idPedido
            );
          }
        );
      } catch (error) {
        setError(
          "Erro ao conectar às notificações."
        );
      } finally {
        setLoading(false);
      }
    }

    conectar();

    return () => {
      eventSource?.close();
    };
  }, []);

  return {
  solicitacoes,
  loading,
  error,
  removerSolicitacao,
  removerSolicitacaoPorPedido,
};
}

