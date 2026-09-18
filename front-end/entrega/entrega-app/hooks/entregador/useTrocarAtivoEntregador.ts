import { useState } from "react";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { alternarStatusEntregador } from "../../service/entregador/entregadorService";

export function useTrocaAtivoEntregador() {
  const [carregando, setCarregando] = useState(false);
  const [erro, setErro] = useState("");

  async function trocarStatus() {
    try {
      setCarregando(true);
      setErro("");
      const usuarioStorage = await AsyncStorage.getItem("@entrega:user");
      if (!usuarioStorage) {
        throw new Error("Usuário não encontrado no storage");
      }
      const usuario = JSON.parse(usuarioStorage);
      if (!usuario.id) {
        throw new Error("ID do entregador não encontrado no storage");
      }
      const response = await alternarStatusEntregador(usuario.id);
      return response;
    } catch (error) {
      const mensagem =
        error instanceof Error
          ? error.message
          : "Erro ao alterar status do entregador";

      setErro(mensagem);
      throw error;
    } finally {
      setCarregando(false);
    }
  }

  return {
    trocarStatus,
    carregando,
    erro,
  };
}