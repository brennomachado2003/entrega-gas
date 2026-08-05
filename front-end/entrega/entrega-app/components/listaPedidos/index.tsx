import React from "react";
import {
  View,
  Text,
  ScrollView,
  ActivityIndicator,
  Pressable,
} from "react-native";

import { styles } from "./style";
import { useListaPedidos } from "../../hooks/pedido/useListaPedidos";
import { useCarrinho } from "../../hooks/carrinho/useCarrinho";

function getStatusStyle(idStatus: number) {
  switch (idStatus) {
    case 1: // Rascunho
      return {
        backgroundColor: "#9CA3AF",
        color: "#FFFFFF",
      };

    case 2: // Disponível
      return {
        backgroundColor: "#3B82F6",
        color: "#FFFFFF",
      };

    case 3: // Aceito
      return {
        backgroundColor: "#F59E0B",
        color: "#FFFFFF",
      };

    case 4: // Finalizado
      return {
        backgroundColor: "#22C55E",
        color: "#FFFFFF",
      };

    case 5: // Cancelado
      return {
        backgroundColor: "#EF4444",
        color: "#FFFFFF",
      };

    default:
      return {
        backgroundColor: "#64748B",
        color: "#FFFFFF",
      };
  }
}

export default function ListaPedidos() {
  const {
    pedidos,
    loading,
    error,
    carregarPedidos,
  } = useListaPedidos();
  const { adicionarProduto, carrinho } = useCarrinho();

  if (loading) {
    return (
      <View style={styles.loadingContainer}>
        <ActivityIndicator size="large" />
      </View>
    );
  }

  if (error) {
    return (
      <View style={styles.emptyContainer}>
        <Text style={styles.emptyText}>
          {error}
        </Text>

        <Pressable
          style={styles.retryButton}
          onPress={carregarPedidos}
        >
          <Text style={styles.retryButtonText}>
            Tentar novamente
          </Text>
        </Pressable>
      </View>
    );
  }

  if (pedidos.length === 0) {
    return (
      <View style={styles.emptyContainer}>
        <Text style={styles.emptyText}>
          Nenhum pedido encontrado
        </Text>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <Text style={styles.title}>
        Histórico de Pedidos
      </Text>

      <ScrollView
        showsVerticalScrollIndicator={false}
        contentContainerStyle={styles.list}
      >
        {pedidos.map((pedido) => {
          const statusStyle = getStatusStyle(pedido.status.idStatus);

          return (
            <View
              key={pedido.idPedido}
              style={styles.card}
            >
              <View style={styles.header}>
              <Text style={styles.pedido}>
                Pedido #{pedido.idPedido}
              </Text>
            </View>

            <Text style={styles.address}>
              {pedido.rua}, {pedido.numero}
            </Text>

            <Text style={styles.address}>
              {pedido.bairro} - {pedido.cidade}
            </Text>

            {pedido.produtos.map((produto, index) => (
              <Text
                key={index}
                style={styles.product}
              >
                🛢️ {produto.nomeProduto}
              </Text>
            ))}

            <View style={styles.footer}>
              <Text style={styles.date}>
                {new Date(
                  pedido.dataPedido
                ).toLocaleDateString("pt-BR")}
              </Text>

              <View>
                 <Text
                  style={[
                    styles.status,
                    {
                      backgroundColor: statusStyle.backgroundColor,
                      color: statusStyle.color,
                    },
                  ]}
                >
                  {pedido.status.tipoStatus}
                </Text>
                <Text style={styles.price}>
                  R$ {pedido.valorCompra.toFixed(2)}
                </Text>
              </View>

              
            </View>
          </View>
        )})}
      </ScrollView>
    </View>
  );
}