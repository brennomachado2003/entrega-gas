import { useState } from "react";
import { atualizarLocalizacao } from "../../service/logistica/rastrear/apiRastreamento";
import { LocalizacaoResponseDTO} from "../../service/logistica/rastrear/types/type";
import { obterLocalizacao } from "../../service/entregador/locationService";

export function useAtualizarLocalizacao(idEntregador: number) {

  const [carregando, setCarregando] = useState(false);
  const [erro, setErro] = useState<string | null>(null);

  async function atualizar(idEntregador: number): Promise<LocalizacaoResponseDTO | null> {
    try {
      setCarregando(true);
      setErro(null);
      const localizacao = await obterLocalizacao();
      const response = await atualizarLocalizacao(idEntregador,localizacao);
      return response;
    } catch (error) {
      setErro(error instanceof Error ? error.message : "Erro ao atualizar localização");
      return null;
    } finally {
      setCarregando(false);
    }
  }
  return {atualizar,carregando,erro};
}

