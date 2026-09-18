import { useState } from "react";
import { Alert } from "react-native";
import AsyncStorage from "@react-native-async-storage/async-storage";

import { cadastrarEndereco } from "../../service/usuario/enderecoService";
import { EnderecoCadastroDTO } from "../../components/endereco/types";

type UsuarioStorage = {
  id: number;
  nome: string;
  contato: string;
  tipo: string;
};

export function useCadastrarEndereco() {
  const [loading, setLoading] = useState(false);

  const [errors, setErrors] = useState({
    rua: false,
    numero: false,
    bairro: false,
    cidade: false,
    estado: false,
    cep: false,
  });

  async function cadastrar(form: EnderecoCadastroDTO) {
    const validacao = validarCadastroEndereco(form);
    setErrors(validacao);

    if (Object.values(validacao).some(Boolean)) {
      Alert.alert("Atenção", "Por favor, corrija os erros.");
      return false;
    }

    setLoading(true);

    try {
      const usuarioStorage = await AsyncStorage.getItem("@entrega:user");

      if (!usuarioStorage) {
        Alert.alert("Erro", "Usuário não encontrado.");
        return false;
      }

      const usuario: UsuarioStorage = JSON.parse(usuarioStorage);

      await cadastrarEndereco({
        ...form,
        usuarioId: usuario.id,
      });

      Alert.alert("Sucesso", "Endereço cadastrado!");
      return true;
    } catch (error) {
      Alert.alert("Erro", "Erro ao cadastrar endereço.");
      return false;
    } finally {
      setLoading(false);
    }
  }

  return {
    cadastrar,
    loading,
    errors,
  };
}

export function validarCadastroEndereco(form: EnderecoCadastroDTO) {
  return {
    rua: !form.rua?.trim(),
    numero: !form.numero?.trim(),
    bairro: !form.bairro?.trim(),
    cidade: !form.cidade?.trim(),
    estado: !form.estado?.trim(),
    cep: !form.cep?.trim(),
  };
}