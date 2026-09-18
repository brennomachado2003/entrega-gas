import { post } from "../../http";
import { AtualizarLocalizacaoDTO, LocalizacaoResponseDTO } from "./types/type";

export function atualizarLocalizacao(idEntregador: number, localizacao: AtualizarLocalizacaoDTO): Promise<LocalizacaoResponseDTO> {
  return post(`/localizacao/${idEntregador}/localizacao`, localizacao);
}

