package org.questao.pedidos.pedido.controller;

import org.questao.pedidos.pedido.dominio.Pedido;
import org.questao.pedidos.pedido.dto.AceitarPedidoRequestDTO;
import org.questao.pedidos.pedido.dto.PedidoEntregaResponseDTO;
import org.questao.pedidos.pedido.dto.PedidoRequest;
import org.questao.pedidos.pedido.dto.PedidoResponseDTO;
import org.questao.pedidos.itemPedido.service.ItemPedidoService;
import org.questao.pedidos.pedido.intraestrutura.PedidoEntity;
import org.questao.pedidos.pedido.intraestrutura.PedidoMapper;
import org.questao.pedidos.pedido.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final CadastrarPedidoService cadastrarPedidoService;
    private final ListarPedidosPorUsuarioService listarPedidosPorUsuarioService;
    private final ListarPedidosPorEntregadorService listarPedidosPorEntregadorService;
    private final CancelarPedidoService cancelarPedidoService;
    private final AceitarPedidoService aceitarPedidoService;

    public PedidoController(CadastrarPedidoService cadastrarPedidoService,
                            ListarPedidosPorUsuarioService listarPedidosPorUsuarioService,
                            ListarPedidosPorEntregadorService listarPedidosPorEntregadorService,
                            AceitarPedidoService aceitarPedidoService,
                            CancelarPedidoService cancelarPedidoService) {
        this.aceitarPedidoService = aceitarPedidoService;
        this.cadastrarPedidoService = cadastrarPedidoService;
        this.listarPedidosPorUsuarioService = listarPedidosPorUsuarioService;
        this.listarPedidosPorEntregadorService = listarPedidosPorEntregadorService;
        this.cancelarPedidoService = cancelarPedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> cadastrar(@RequestBody PedidoRequest pedidoRequest) {
        try {
            Pedido pedido = cadastrarPedidoService.criarPedido(pedidoRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(PedidoMapper.produtoItemPedidoDTO(pedido, "Pedido criado"));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/historico")
    public ResponseEntity<List<PedidoEntregaResponseDTO>> listaHistorico(@RequestParam("usuarioId") Long usuarioId) {
        List<Pedido> pedidos = listarPedidosPorUsuarioService.listarPedidosPorUsuario(usuarioId);
        if (pedidos == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        List<PedidoEntregaResponseDTO> pedidosEntregaResponseDTO = pedidos.stream().map(PedidoMapper::pedidoEntregaResponseDTO).toList();
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidosEntregaResponseDTO);
    }

    @GetMapping("/historico/entrega")
    public ResponseEntity<List<PedidoEntregaResponseDTO>> listaEntrega(@RequestParam("entregadorId") Long entregadorId) {
        List<Pedido> pedidos = listarPedidosPorEntregadorService.listaPedidosPorEntregador(entregadorId);
        if (pedidos == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        List<PedidoEntregaResponseDTO> pedidosEntregaResponseDTO = pedidos.stream().map(PedidoMapper::pedidoEntregaResponseDTO).toList();
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidosEntregaResponseDTO);
    }

    @PatchMapping("/cancelarPedido")
    public ResponseEntity<PedidoResponseDTO> cancelarPedido(@RequestBody Long pedidoId) {
        try {
            Pedido pedido = cancelarPedidoService.cancelarPedido(pedidoId);
            return ResponseEntity.status(HttpStatus.OK).body(PedidoMapper.produtoItemPedidoDTO(pedido, "Pedido cancelado"));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PatchMapping("/entregadorDefinido")
    public ResponseEntity<PedidoResponseDTO> entregadoDefinido(@RequestBody AceitarPedidoRequestDTO aceitarPedidoRequestDTO) {
        Pedido pedido = aceitarPedidoService.aceitarPedido(aceitarPedidoRequestDTO.pedidoId(), aceitarPedidoRequestDTO.entregadorId());
        return ResponseEntity.status(HttpStatus.OK).body(PedidoMapper.produtoItemPedidoDTO(pedido, "Pedido aceitado"));
    }
}
