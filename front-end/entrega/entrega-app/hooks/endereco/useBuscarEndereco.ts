import { useEffect, useState } from "react";
import { Alert } from "react-native";
import AsyncStorage from "@react-native-async-storage/async-storage";

import { buscarEnderecosPorUsuario } from "../../service/usuario/enderecoService";
import { EnderecoResponseDTO } from "../../components/endereco/types";

type UsuarioStorage = {
  id: number;
  nome: string;
  contato: string;
  tipo: string;
};

export function useBuscarEndereco() {
  const [enderecos, setEnderecos] = useState<EnderecoResponseDTO[]>([]);
  const [loading, setLoading] = useState(false);

  async function buscar() {
    setLoading(true);

    try {
      const usuarioStorage = await AsyncStorage.getItem("@entrega:user");

      if (!usuarioStorage) {
        setEnderecos([]);
        return;
      }

      const usuario: UsuarioStorage = JSON.parse(usuarioStorage);

      const response = await buscarEnderecosPorUsuario(usuario.id);
      setEnderecos(response);
    } catch (error) {
      Alert.alert("Erro", "Erro ao buscar endereços.");
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    buscar();
  }, []);

  return {
    enderecos,
    loading,
    buscar,
  };
}