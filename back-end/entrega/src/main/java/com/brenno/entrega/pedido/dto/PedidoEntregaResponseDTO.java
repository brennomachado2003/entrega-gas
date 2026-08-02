package com.brenno.entrega.pedido.dto;

import com.brenno.entrega.pedido.itemPedido.dto.ProdutoItemPedidoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public record PedidoEntregaResponseDTO (
    Integer idPedido,
    String nomeCliente,
    String telefoneCliente,
    String rua,
    String numero,
    String bairro,
    String cidade,
    BigDecimal valorCompra,
    List<ProdutoItemPedidoDTO> produtos,
    LocalDateTime dataPedido
){ }