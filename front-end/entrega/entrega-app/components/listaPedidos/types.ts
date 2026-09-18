

export interface AceitarPedidoRequestDTO {
    pedidoId: number;
    entregadorId: number;
}


export interface HistoricoRequestDTO {
    usuarioId: number;
}

export interface HistoricoEntregadorRequestDTO {
    entregadorId: number;
}

export interface ItemPedidoResponseDTO {
    produtoId: number;
    quantidade: number;
    preco: number;
    desconto: number;
}

export interface StatusPedidoDTO {
    idStatus: number;
    tipoStatus: string;
}

export interface PedidoEntregaResponseDTO {
    idPedido: number;
    idCliente: number;
    idEndereco: number;
    valorCompra: number;
    produtos: ItemPedidoResponseDTO[];
    dataPedido: string;
    status: StatusPedidoDTO;
}