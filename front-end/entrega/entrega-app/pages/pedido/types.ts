export type ItemPedidoRequest = {
  produtoId: number;
  quantidade: number;
  desconto: number;
};

export type PedidoRequest = {
  usuarioId: number;
  empresaId: number;
  enderecoId: number;
  valorCompra: number;
  produtos: ItemPedidoRequest[];
};

export type PedidoResponseDTO = {
  pedidoId: number;
  mensagem: string;
};