export type ProdutoItemPedidoDTO = {
  nome: string;
  quantidade: number;
  valorUnitario: number;
};

export type PedidoEntregaResponseDTO = {
  idPedido: number;
  nomeCliente: string;
  telefoneCliente: string;
  rua: string;
  numero: string;
  bairro: string;
  cidade: string;
  valorCompra: number;
  produtos: ProdutoItemPedidoDTO[];
  dataPedido: string;
};

export type SolicitacaoEntregaResponseDTO = {
  idSolicitacao: number;
  idPedido: number;
  idEntregador: number;
  status: string;
  dataSolicitacao: string;
};

export type SolicitacaoEntregaCriadaEvent = {
  idSolicitacao: number;
  idPedido: number;
  idEntregador: number;
  ocorridoEm: string;
};

export type SolicitacaoEntregaAceitaEvent = {
        idEntregador: number,
        idPedido: number,
        ocorridoEm: string
};
