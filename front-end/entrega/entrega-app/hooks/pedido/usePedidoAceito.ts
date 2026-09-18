import { useEffect, useState } from "react";

import { conectarPedidosSSE } from "../../service/pedido/pedidosService";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";
import { UsuarioStackParamList } from "../../navigation/stack/UsuarioStack";

type NavigationProp = NativeStackNavigationProp<
  UsuarioStackParamList,
  "SolicitarEntregador"
>;

export function usePedidoAceito(
  idPedido: number,
  navigation: NavigationProp
) {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    let eventSource: EventSource | null = null;

    function conectar() {
      try {
        setLoading(true);
        setError("");

        eventSource = conectarPedidosSSE(idPedido);
        eventSource.onerror = (erro) => {
          console.error(
            "❌ ERRO SSE PEDIDO:",
            erro
          );

          setError(
            "Erro na conexão com o pedido."
          );
        };

        eventSource.addEventListener(
          "pedido-aceito",
          (event) => {
            const dados = JSON.parse(event.data);
            navigation.navigate("Pedido", {
              pedidoId: dados.idPedido,
            });
          }
        );

      } catch (error) {
        console.error(
          "❌ Erro ao conectar SSE:",
          error
        );

        setError(
          "Erro ao conectar ao pedido."
        );
      } finally {
        setLoading(false);
      }
    }

    if (idPedido) {
      conectar();
    }

    return () => {
      eventSource?.close();
    };
  }, [idPedido, navigation]);

  return {
    loading,
    error,
  };
}