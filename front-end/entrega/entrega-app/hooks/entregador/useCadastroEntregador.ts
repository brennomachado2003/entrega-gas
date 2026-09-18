import { useState } from "react";
import { EntregadorCadastroDTO } from "../../pages/cadastro/entregador/types";
import {validarCadastroEntregador} from "../../util/validacao/entregador/validarCadastroUsuario";
import { Alert } from "react-native";
import {cadastrarEntregador} from "../../service/entregador/entregadorService";
import { formatPraEnviar } from "../../util/inputFormatters";

export function useCadastroEntregador() {
    const [loading, setLoading] = useState(false);
    const [errors, setErrors] = useState({nome: false, cpf: false, telefone: false, senha: false,});

    async function cadastrar(form: EntregadorCadastroDTO) {
        setErrors(validarCadastroEntregador(form));
        if (Object.values(validarCadastroEntregador(form)).some(Boolean)) {
          Alert.alert("Atenção", "Por favor, corrija os erros.");
          return false;
        }
        setLoading(true);
        try {
          await cadastrarEntregador(formatPraEnviar(form));
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
      return {cadastrar, loading, errors};
}

    