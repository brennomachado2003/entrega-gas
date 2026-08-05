export interface HistoricoRequestDTO {
    usuarioId: number;
}

export interface ProdutoItemPedidoDTO {
    idProduto: number;
    nomeProduto: string;
    quantidade: number;
    valorUnitario: number;
}

export interface StatusPedidoDTO {
    idStatus: number;
    tipoStatus: string;
}

export interface PedidoEntregaResponseDTO {
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
    status: StatusPedidoDTO;
}