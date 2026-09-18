import React from "react";
import {
  ScrollView,
  View,
  Text,
} from "react-native";

import { RouteProp, useRoute } from "@react-navigation/native";

import { UsuarioStackParamList } from "../../../navigation/stack/UsuarioStack";
import { styles } from "./style";

type PedidoRouteProp = RouteProp<
  UsuarioStackParamList,
  "Pedido"
>;

export default function Pedido() {
  const route = useRoute<PedidoRouteProp>();

  const { pedidoId } = route.params;

  return (
    <ScrollView
      style={styles.screen}
      contentContainerStyle={styles.container}
      showsVerticalScrollIndicator={false}
    >
      {/* TÍTULO */}
      <Text style={styles.title}>
        Ficha da Entrega
      </Text>

      {/* PEDIDO */}
      <View style={styles.card}>
        <Text style={styles.cardTitle}>
          Pedido #{pedidoId}
        </Text>

        <View style={styles.statusContainer}>
          <Text style={styles.statusLabel}>
            Status
          </Text>

          <Text style={styles.statusValue}>
            Em rota
          </Text>
        </View>
      </View>

      {/* ENDEREÇO */}
      <View style={styles.card}>
        <Text style={styles.cardTitle}>
          Endereço de entrega
        </Text>

        <Text style={styles.infoText}>
          Endereço do pedido
        </Text>
      </View>

      {/* PRODUTOS */}
      <View style={styles.card}>
        <Text style={styles.cardTitle}>
          Produtos
        </Text>

        <View style={styles.productRow}>
          <View>
            <Text style={styles.productName}>
              Produto
            </Text>

            <Text style={styles.productQuantity}>
              Quantidade: 1
            </Text>
          </View>

          <Text style={styles.productPrice}>
            R$ 0,00
          </Text>
        </View>
      </View>

      {/* RESUMO */}
      <View style={styles.card}>
        <Text style={styles.cardTitle}>
          Resumo do pedido
        </Text>

        <View style={styles.row}>
          <Text style={styles.label}>
            Subtotal
          </Text>

          <Text style={styles.value}>
            R$ 0,00
          </Text>
        </View>

        <View style={styles.divider} />

        <View style={styles.row}>
          <Text style={styles.totalLabel}>
            Total
          </Text>

          <Text style={styles.totalValue}>
            R$ 0,00
          </Text>
        </View>
      </View>

      {/* ENTREGADOR */}
      <View style={styles.card}>
        <Text style={styles.cardTitle}>
          Entregador
        </Text>

        <Text style={styles.infoText}>
          Entregador responsável pela entrega
        </Text>
      </View>
    </ScrollView>
  );
}

