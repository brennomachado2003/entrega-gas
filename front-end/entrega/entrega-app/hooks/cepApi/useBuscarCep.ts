import { useState } from "react";
import { apiService } from "../../service/logistica/buscarLocalizacao/apiBuscarCep";
import { validarCep } from "../../util/validacao/cep/validarCep";

export function useBuscarCep() {
  const [buscandoCep, setBuscandoCep] = useState(false);

  const buscarCep = async (cep: string) => {
    const cepLimpo = validarCep(cep);
    if (!cepLimpo) return null;
    setBuscandoCep(true);
    try {
      return await apiService.buscarEnderecoPorCEP(cepLimpo);
    } finally {
      setBuscandoCep(false);
    }
  };

  return {
    buscarCep,
    buscandoCep,
  };
}