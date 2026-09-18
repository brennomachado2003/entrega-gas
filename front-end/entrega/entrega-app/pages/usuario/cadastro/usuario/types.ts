export interface UsuarioCadastroDTO {
  nome: string;
  cpf: string;
  dataNascimento: string;
  telefone: string;
  email: string;
  senha: string;
}

export interface UsuarioResponseDTO {
  idUsuario: number;
  nome: string;
  email: string;
}