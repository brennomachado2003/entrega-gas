import { useState } from "react";
import { Alert } from "react-native";
import { UsuarioCadastroDTO } from "../../pages/cadastro/usuario/types";
import { cadastrarUsuario } from "../../service/usuario/usuarioService";
import { formatPraEnviar }
from "../../util/inputFormatters";
import { validarCadastroUsuario }
from "../../util/validacao/usuario/validarCadastroUsuario";

export function useCadastroUsuario() {
  const [loading, setLoading] = useState(false);
  const [errors, setErrors] = useState({nome: false, cpf: false, dataNascimento: false, telefone: false, email: false, senha: false});

  async function cadastrar(form: UsuarioCadastroDTO) {
    setErrors(validarCadastroUsuario(form));
    if (Object.values(validarCadastroUsuario(form)).some(Boolean)) {
      Alert.alert("Atenção", "Por favor, corrija os erros.");
      return false;
    }
    setLoading(true);
    try {
      await cadastrarUsuario(formatPraEnviar(form));
      Alert.alert("Sucesso", "Cadastro realizado!");
      return true;
    } 
    catch (error) {
        Alert.alert("Erro", "Erro ao cadastrar.");
        return false;
    } finally {
      setLoading(false);
    }
  }
  return {cadastrar, loading, errors,};
}