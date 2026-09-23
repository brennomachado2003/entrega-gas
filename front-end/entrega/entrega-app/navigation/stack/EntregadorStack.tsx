import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { useEffect } from "react";

import { HomeEntregador } from "../../pages";

import { useRastreamentoLocalizacao } from "../../hooks/logistica/useRastreamentoLocalizacao";
import { useNotificarEntregador } from "../../hooks/notificacao/useNotificarEntregador";
import { useAuthContext } from "../../hooks/auth/useAuthContext";

import Notificacao from "../../components/notificacao/notificarEntrega";

import { useExpirarNotificacao } from "../../hooks/notificacao/useExpirarNoficicacao";
import { useRecusarNotificacao } from "../../hooks/notificacao/useRecusarNotificacao";
import { useAceitarNotificacao } from "../../hooks/notificacao/useAceitarNotificacao";

const Stack = createNativeStackNavigator();

export default function EntregadorStack() {

  const { user } = useAuthContext();

  const {aceitar, loading: aceitando,} = useAceitarNotificacao();

  const {expirar, loading: expirando,} = useExpirarNotificacao();

  const {recusar, loading: recusando,} = useRecusarNotificacao();

  const {solicitacoes, loading, error, removerSolicitacao,} = useNotificarEntregador();

  useRastreamentoLocalizacao(user!.id, user!.ativo);

  const solicitacao = solicitacoes[0];


  useEffect(() => {
    if (!solicitacao) return;
    const timer = setTimeout(async () => {
      const sucesso = await expirar(solicitacao.idSolicitacao);
      if (sucesso) removerSolicitacao(solicitacao.idSolicitacao);
    }, 5000);
    return () => {
      clearTimeout(timer);
    };
  }, [solicitacao, expirar, removerSolicitacao]);


  return (
    <>
      <Stack.Navigator>

        <Stack.Screen
          name="HomeEntregador"
          component={HomeEntregador}
        />

      </Stack.Navigator>


      <Notificacao
        visivel={!!solicitacao}
        pedidoId={
          solicitacao?.idPedido ?? 0
        }
        onAceitar={async () => {
          if (!solicitacao) return;
          const idSolicitacao = solicitacao.idSolicitacao;
          const sucesso = await aceitar(idSolicitacao);
          if (sucesso) removerSolicitacao(idSolicitacao);
        }}
        onRecusar={async () => {
          if (!solicitacao) return;
          const idSolicitacao = solicitacao.idSolicitacao;
          const sucesso = await recusar(idSolicitacao);
          if (sucesso) removerSolicitacao(idSolicitacao);
        }}

      />

    </>
  );
}
