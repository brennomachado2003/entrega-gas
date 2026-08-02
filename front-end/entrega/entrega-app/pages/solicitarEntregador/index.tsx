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

import { styles } from "./style";
import { useSolicitarEntregador } from "../../hooks/notificacao/useSolicitarEntregador";

export default function SolicitarEntregador() {
  const route = useRoute();
  const navigation = useNavigation();

  const { pedidoId } = route.params as {
    pedidoId: number;
  };

  const {
    solicitar,
    loading,
    error,
  } = useSolicitarEntregador();

  useEffect(() => {
    async function iniciarBusca() {
      await solicitar(pedidoId);
    }

    iniciarBusca();
  }, [pedidoId]);

  function cancelarPedido() {
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

        {!loading && !error && (
          <Text style={styles.status}>
            Aguardando um entregador aceitar...
          </Text>
        )}

        {error !== "" && (
          <Text style={styles.error}>
            {error}
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
      >
        <Text style={styles.cancelButtonText}>
          CANCELAR PEDIDO
        </Text>
      </Pressable>
    </ScrollView>
  );
}