import { useState } from "react";
import { Alert } from "react-native";

import {cancelarPedido} from "../../service/pedidosService";
import { PedidoResponseDTO } from "../../pages/pedido/types";

export function useCancelarPedido() {
  const [loading, setLoading] = useState(false);

  async function cancelar(
    pedidoId: number
  ) : Promise<PedidoResponseDTO | null> {
    setLoading(true);
    try {
      const pedidoCancelado = await cancelarPedido(pedidoId);
      Alert.alert("Sucesso", "Pedido cancelado com sucesso!");
      return pedidoCancelado;
    } 
    catch (error) {
      console.log("erro cancelar pedido", error);
      Alert.alert("Erro", "Erro ao cancelar pedido.");
      return null;
    } 
    finally {
      setLoading(false);
    }
  }

  return {
    cancelar,
    loading,
  };
}