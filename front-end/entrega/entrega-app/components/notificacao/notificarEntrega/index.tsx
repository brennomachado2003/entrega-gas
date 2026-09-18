import React from "react";
import { View, Text, Pressable } from "react-native";
import { styles } from "./style";

type Props = {
  visivel: boolean;
  pedidoId: number;
  onAceitar: () => void;
  onRecusar: () => void;
};

export default function Notificacao({
  visivel,
  pedidoId,
  onAceitar,
  onRecusar,
}: Props) {

  if (!visivel) {
    return null;
  }

  return (
    <View style={styles.container}>

      <Text style={styles.titulo}>
        🔔 Nova Solicitação
      </Text>

      <Text style={styles.mensagem}>
        Pedido #{pedidoId}
      </Text>

      <View style={styles.botoes}>

        <Pressable
          style={styles.botaoAceitar}
          onPress={onAceitar}
        >
          <Text style={styles.textoBotao}>
            Aceitar
          </Text>
        </Pressable>

        <Pressable
          style={styles.botaoRecusar}
          onPress={onRecusar}
        >
          <Text style={styles.textoBotao}>
            Recusar
          </Text>
        </Pressable>

      </View>
    </View>
  );
}

