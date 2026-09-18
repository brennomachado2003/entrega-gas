export type AtualizarLocalizacaoDTO = {
  latitude: number;
  longitude: number;
};

export type LocalizacaoResponseDTO = {
  idEntregador: number;
  latitude: number;
  longitude: number;
  data: string;
};