package com.brenno.entrega.pedido.controller;

import com.brenno.entrega.pedido.dto.AceitarPedidoRequestDTO;
import com.brenno.entrega.pedido.dto.PedidoEntregaResponseDTO;
import com.brenno.entrega.pedido.dto.PedidoRequest;
import com.brenno.entrega.pedido.dto.PedidoResponseDTO;
import com.brenno.entrega.pedido.itemPedido.service.ItemPedidoService;
import com.brenno.entrega.pedido.model.Pedido;
import com.brenno.entrega.pedido.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    private final PedidoService pedidoService;
    private final ItemPedidoService pedidoProdutoService;

    public PedidoController(PedidoService pedidoService,
                            ItemPedidoService pedidoProdutoService) {
        this.pedidoService = pedidoService;
        this.pedidoProdutoService = pedidoProdutoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> cadastrar(@RequestBody PedidoRequest pedidoRequest) {
        try {
            Pedido pedido = pedidoService.criarPedido(pedidoRequest);
            pedidoProdutoService.adicionandoProdutoAoPedido(pedido, pedidoRequest.produtos());
            PedidoResponseDTO response = new PedidoResponseDTO(pedido.getIdPedido(), "Pedido criado com sucesso");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/historico")
    public ResponseEntity<List<PedidoEntregaResponseDTO>> listaHistorico(Integer usuarioId) {
        List<Pedido> pedidos = pedidoService.listaPedidosPorUsuario(usuarioId);
        if (pedidos == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<PedidoEntregaResponseDTO> pedidoEntregaResponseDTO = pedidos.stream().map(pedidoService::PedidoParaListaPedidoResponseDTO).toList();
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoEntregaResponseDTO);
    }

    @PatchMapping("/cancelarPedido")
    public ResponseEntity<PedidoResponseDTO> cancelarPedido(@RequestBody Integer pedidoId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.cancelarPedido(pedidoId));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PatchMapping("/entregadorDefinido")
    public ResponseEntity<PedidoResponseDTO> entregadoDefinido(@RequestBody AceitarPedidoRequestDTO aceitarPedidoRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.aceitarPedido(aceitarPedidoRequestDTO.pedidoId(), aceitarPedidoRequestDTO.entregadorId()));
    }
}
