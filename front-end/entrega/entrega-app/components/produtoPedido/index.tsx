import React from "react";
import {
  View,
  Text,
  ActivityIndicator,
} from "react-native";

import { useBuscarProduto } from "../../hooks/produto/useBuscarProduto";
import { styles } from "./style";

type Props = {
  produtoId: number;
  quantidade: number;
};

export default function ProdutoPedido({
  produtoId,
  quantidade,
}: Props) {
  const {
    produto,
    loading,
    error,
  } = useBuscarProduto(produtoId);

  if (loading) {
    return (
      <View style={styles.container}>
        <ActivityIndicator
          size="small"
          style={styles.loading}
        />
      </View>
    );
  }

  if (error) {
    return (
      <Text style={styles.error}>
        Erro ao carregar produto
      </Text>
    );
  }

  return (
    <Text style={styles.product}>
      🛢️ {produto?.nome} - Quantidade: {quantidade}
    </Text>
  );
}