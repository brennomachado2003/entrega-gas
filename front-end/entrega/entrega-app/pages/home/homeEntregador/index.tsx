import React, { useEffect, useRef, useState } from "react";
import {
  ScrollView,
  View,
  Text,
  Pressable,
  Platform,
  Alert,
} from "react-native";

import { styles } from "./style";

import {
  ListPedidosEntrega,
  Saudacao,
  NotificacarEntrega,
} from "../../../components";

import { useTrocaAtivoEntregador } from "../../../hooks/entregador/useTrocarAtivoEntregador";

import { useNotificarEntregador } from "../../../hooks/notificacao/useNotificarEntregador";
import { useAceitarNotificacao } from "../../../hooks/notificacao/useAceitarNotificacao";
import { useRecusarNotificacao } from "../../../hooks/notificacao/useRecusarNotificacao";
import { useExpirarNotificacao } from "../../../hooks/notificacao/useExpirarNoficicacao";

import { SolicitacaoEntregaResponseDTO } from "../../../service/notificacaoService";

export default function HomeEntregador() {
  const [isOnline, setIsOnline] = useState(false);

  const [notificacao, setNotificacao] =
    useState<SolicitacaoEntregaResponseDTO | null>(null);

  const notificacoesJaExibidas = useRef<number[]>([]);
  const timeoutRef = useRef<ReturnType<typeof setTimeout> | null>(null);

  const { trocarStatus, carregando } = useTrocaAtivoEntregador();

  const { buscarNotificacoes } = useNotificarEntregador();
  const { aceitar } = useAceitarNotificacao();
  const { recusar } = useRecusarNotificacao();
  const { expirar } = useExpirarNotificacao();

  async function handleTrocarStatus() {
    try {
      const entregadorAtualizado = await trocarStatus();

      setIsOnline(entregadorAtualizado.ativo);
    } catch (error) {
      Alert.alert(
        "Erro",
        "Não foi possível alterar seu status."
      );
    }
  }

  async function handleAceitar() {
    if (!notificacao) {
      return;
    }

    try {
      if (timeoutRef.current) {
        clearTimeout(timeoutRef.current);
      }

      const pedido = await aceitar(
        notificacao.idSolicitacao
      );

      if (pedido) {
        setNotificacao(null);

        console.log("Pedido aceito", pedido);
      }
    } catch (error) {
      console.log(error);
    }
  }

  async function handleRecusar() {
    if (!notificacao) {
      return;
    }

    try {
      if (timeoutRef.current) {
        clearTimeout(timeoutRef.current);
      }

      const sucesso = await recusar(
        notificacao.idSolicitacao
      );

      if (sucesso) {
        setNotificacao(null);
      }
    } catch (error) {
      console.log(error);
    }
  }

  useEffect(() => {
    if (!isOnline) {
      return;
    }

    async function verificarSolicitacoes() {
      try {
        const solicitacoes =
          await buscarNotificacoes();

        const novaSolicitacao = solicitacoes.find(
          solicitacao =>
            !notificacoesJaExibidas.current.includes(
              solicitacao.idSolicitacao
            )
        );

        if (!novaSolicitacao) {
          return;
        }

        notificacoesJaExibidas.current.push(
          novaSolicitacao.idSolicitacao
        );

        setNotificacao(novaSolicitacao);
      } catch (error) {
        console.log(error);
      }
    }

    verificarSolicitacoes();

    const interval = setInterval(
      verificarSolicitacoes,
      5000
    );

    return () => clearInterval(interval);
  }, [isOnline]);

  useEffect(() => {
    if (!notificacao) {
      return;
    }

    timeoutRef.current = setTimeout(async () => {
      try {
        const sucesso = await expirar(
          notificacao.idSolicitacao
        );

        if (sucesso) {
          setNotificacao(null);
        }
      } catch (error) {
        console.log(error);
      }
    }, 10000);

    return () => {
      if (timeoutRef.current) {
        clearTimeout(timeoutRef.current);
      }
    };
  }, [notificacao]);

  return (
    <>
      <NotificacarEntrega
        visivel={!!notificacao}
        pedidoId={notificacao?.idPedido ?? 0}
        onAceitar={handleAceitar}
        onRecusar={handleRecusar}
      />

      <ScrollView
        style={styles.screen}
        contentContainerStyle={styles.container}
        showsVerticalScrollIndicator={false}
      >
        {Platform.OS !== "web" && <Saudacao />}

        <View
          style={[
            styles.heroCard,
            isOnline && styles.heroCardOnline,
          ]}
        >
          <Text style={styles.heroIcon}>
            {isOnline ? "🚗" : "💤"}
          </Text>

          <Text style={styles.heroTitle}>
            {isOnline
              ? "Você está Online"
              : "Você está Offline"}
          </Text>

          <Text style={styles.heroDescription}>
            {isOnline
              ? "Aguarde novas solicitações de gás na sua região. Mantenha o app aberto."
              : "Fique online para começar a receber pedidos e realizar entregas hoje."}
          </Text>

          <Pressable
            style={[
              styles.heroButton,
              isOnline &&
                styles.heroButtonOffline,
            ]}
            onPress={handleTrocarStatus}
            disabled={carregando}
          >
            <Text
              style={[
                styles.heroButtonText,
                isOnline &&
                  styles.heroButtonTextOffline,
              ]}
            >
              {carregando
                ? "ALTERANDO..."
                : isOnline
                ? "FICAR OFFLINE"
                : "FICAR ONLINE AGORA"}
            </Text>
          </Pressable>
        </View>

        <Text style={styles.sectionTitle}>
          Seu Desempenho (Hoje)
        </Text>

        <View style={styles.features}>
          <View style={styles.featureCard}>
            <Text style={styles.featureIcon}>
              💰
            </Text>
            <Text style={styles.featureText}>
              R$ 0,00
            </Text>
            <Text style={styles.featureSubText}>
              Ganhos
            </Text>
          </View>

          <View style={styles.featureCard}>
            <Text style={styles.featureIcon}>
              📦
            </Text>
            <Text style={styles.featureText}>
              0
            </Text>
            <Text style={styles.featureSubText}>
              Entregas
            </Text>
          </View>

          <View style={styles.featureCard}>
            <Text style={styles.featureIcon}>
              ⭐
            </Text>
            <Text style={styles.featureText}>
              5.0
            </Text>
            <Text style={styles.featureSubText}>
              Avaliação
            </Text>
          </View>
        </View>

        <Text style={styles.sectionTitle}>
          Próximas Entregas / Histórico
        </Text>

        <ListPedidosEntrega />
      </ScrollView>
    </>
  );
}