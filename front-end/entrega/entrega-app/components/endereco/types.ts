export type EnderecoCadastroDTO = {
  usuarioId: number;
  rua: string;
  numero: string;
  complemento?: string;
  bairro: string;
  cidade: string;
  estado: string;
  cep: string;
  latitude?: number;
  longitude?: number;
};

export type EnderecoResponseDTO = {
  idEndereco: number;
  rua: string;
  numero: string;
  bairro: string;
  cidade: string;
  estado: string;
};

export type EnderecoProps = {
  enderecos: EnderecoResponseDTO[];
  enderecoSelecionado?: EnderecoResponseDTO | null;
  onSelecionarEndereco: (endereco: EnderecoResponseDTO) => void;
  onEnderecoCadastrado?: () => void;
};