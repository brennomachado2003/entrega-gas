import React, { useEffect } from "react";
import {
  View,
  Text,
  ScrollView,
  ActivityIndicator,
  Pressable,
} from "react-native";

import {
  useRoute,
  useNavigation,
} from "@react-navigation/native";

import { NativeStackNavigationProp } from "@react-navigation/native-stack";

import { styles } from "./style";

import { useSolicitarEntregador } from "../../../hooks/notificacao/useSolicitarEntregador";
import { useCancelarPedido } from "../../../hooks/pedido/useCancelarPedido";
import { usePedidoAceito } from "../../../hooks/pedido/usePedidoAceito";

import { UsuarioStackParamList } from "../../../navigation/stack/UsuarioStack";

type NavigationProp = NativeStackNavigationProp<
  UsuarioStackParamList,
  "SolicitarEntregador"
>;

export default function SolicitarEntregador() {
  const route = useRoute();
  const navigation = useNavigation<NavigationProp>();

  const { pedidoId } = route.params as {
    pedidoId: number;
  };

  const {
    solicitar,
    loading,
    error,
  } = useSolicitarEntregador();

  const {
    cancelar,
    loading: loadingCancelamento,
  } = useCancelarPedido();

  // Abre a comunicação SSE e aguarda o entregador aceitar
  const {
    loading: loadingSSE,
    error: errorSSE,
  } = usePedidoAceito(pedidoId, navigation);

  useEffect(() => {
    async function iniciarBusca() {
      await solicitar(pedidoId);
    }

    iniciarBusca();
  }, [pedidoId]);

  async function cancelarPedido() {
    const pedido = await cancelar(pedidoId);

    if (!pedido) {
      return;
    }

    navigation.goBack();
  }

  return (
    <ScrollView
      style={styles.screen}
      contentContainerStyle={styles.container}
      showsVerticalScrollIndicator={false}
    >
      <View style={styles.card}>
        <Text style={styles.icon}>🚚</Text>

        <Text style={styles.title}>
          Procurando entregador
        </Text>

        <Text style={styles.description}>
          Estamos notificando os entregadores próximos.
          Assim que alguém aceitar, você será avisado automaticamente.
        </Text>

        <ActivityIndicator
          size="large"
          color="#FBBF24"
          style={styles.loading}
        />

        {loading && (
          <Text style={styles.status}>
            Enviando solicitações...
          </Text>
        )}

        {!loading && !error && !errorSSE && (
          <Text style={styles.status}>
            Aguardando um entregador aceitar...
          </Text>
        )}

        {error !== "" && (
          <Text style={styles.error}>
            {error}
          </Text>
        )}

        {errorSSE !== "" && (
          <Text style={styles.error}>
            {errorSSE}
          </Text>
        )}
      </View>

      <View style={styles.infoCard}>
        <Text style={styles.infoTitle}>
          Pedido
        </Text>

        <Text style={styles.infoText}>
          #{pedidoId}
        </Text>
      </View>

      <Pressable
        style={styles.cancelButton}
        onPress={cancelarPedido}
        disabled={loadingCancelamento}
      >
        <Text style={styles.cancelButtonText}>
          {loadingCancelamento
            ? "CANCELANDO..."
            : "CANCELAR PEDIDO"}
        </Text>
      </Pressable>
    </ScrollView>
  );
}

