export interface EntregadorCadastroDTO {
  nome: string;
  cpf: string;
  senha: string;
  telefone: string;
}

export interface EntregadorResponseDTO {
  idEntregador: number;
  nome: string;
  telefone: string;
  ativo: boolean;
}