package com.brenno.entrega.pedido.service;

import com.brenno.entrega.empresa.model.Empresa;
import com.brenno.entrega.pedido.dto.PedidoEntregaResponseDTO;
import com.brenno.entrega.pedido.dto.PedidoRequest;
import com.brenno.entrega.pedido.dto.PedidoResponseDTO;
import com.brenno.entrega.pedido.exception.PedidoJaAceitoException;
import com.brenno.entrega.pedido.itemPedido.dto.ProdutoItemPedidoDTO;
import com.brenno.entrega.pedido.itemPedido.model.ItemPedido;
import com.brenno.entrega.pedido.itemPedido.service.ItemPedidoService;
import com.brenno.entrega.pedido.model.Pedido;
import com.brenno.entrega.pedido.repository.PedidoRepository;
import com.brenno.entrega.empresa.service.EmpresaService;
import com.brenno.entrega.pedido.statusPedido.model.StatusPedido;
import com.brenno.entrega.user.endereco.service.EnderecoService;
import com.brenno.entrega.pedido.statusPedido.service.StatusPedidoService;
import com.brenno.entrega.user.endereco.modal.Endereco;
import com.brenno.entrega.user.model.Usuario;
import com.brenno.entrega.user.service.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final UsuarioService usuarioService;
    private final EmpresaService empresaService;
    private final StatusPedidoService statusPedidoService;
    private final EnderecoService enderecoService;
    private final ItemPedidoService itemPedidoService;
    public PedidoService(PedidoRepository pedidoRepository, UsuarioService usuarioService,
                         EmpresaService empresaService, StatusPedidoService statusPedidoService,
                         EnderecoService enderecoService, ItemPedidoService itemPedidoService) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioService = usuarioService;
        this.empresaService = empresaService;
        this.statusPedidoService = statusPedidoService;
        this.enderecoService = enderecoService;
        this.itemPedidoService = itemPedidoService;

    }

    public List<Pedido> listaPedidosPorUsuario(Integer usuarioId) {
        return pedidoRepository.findByUsuarioIdUsuarioOrderByDataPedidoDesc(usuarioId);
    }
    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }
    public Pedido findById(Integer id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }
    public void delete(Pedido pedido) {
        pedidoRepository.delete(pedido);
    }

    public Pedido aberturaPedido(Usuario usuario, Empresa empresa, StatusPedido statusPedido, Endereco endereco) {
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setEmpresa(empresa);
        pedido.setStatus(statusPedido);
        pedido.setEndereco(endereco);
        return save(pedido);
    }
    public Pedido criarPedido(PedidoRequest request) {
        Usuario usuario = usuarioService.findById(request.usuarioId());
        Empresa empresa = empresaService.findById(request.empresaId());
        StatusPedido status = statusPedidoService.findById(2);
        Endereco endereco = enderecoService.findById(request.enderecoId());
        Pedido pedido = new Pedido(usuario, empresa, status, endereco, request.valorCompra());
        return pedidoRepository.save(pedido);
    }
    public PedidoResponseDTO cancelarPedido(Integer pedidoId) {
        Pedido pedido = findById(pedidoId);
        StatusPedido statusPedido = statusPedidoService.findById(5);
        pedido.setStatus(statusPedido);
        save(pedido);
        return PedidoParaListaPedidoResponse(save(pedido), "Pedido cancelado");
    }
    public PedidoResponseDTO PedidoParaListaPedidoResponse(Pedido pedido, String mensagem) {
        return new PedidoResponseDTO(pedido.getIdPedido(), mensagem);
    }
    public PedidoEntregaResponseDTO PedidoParaListaPedidoResponseDTO(Pedido pedido) {
        List<ItemPedido> itens = pedido.getItens();
        List<ProdutoItemPedidoDTO> produtoItems = itens.stream().map(itemPedidoService::itemParaPedidoEntregaResponseDTO).toList();
        return PedidoParaPedidoEntregaResponseDTO(pedido, produtoItems);
    }
    public PedidoEntregaResponseDTO PedidoParaPedidoEntregaResponseDTO(Pedido pedido, List<ProdutoItemPedidoDTO> produtos) {
        return new PedidoEntregaResponseDTO(
                pedido.getIdPedido(),
                pedido.getUsuario().getNome(),
                pedido.getUsuario().getTelefone().getNumero(),
                pedido.getEndereco().getRua(),
                pedido.getEndereco().getNumero(),
                pedido.getEndereco().getBairro(),
                pedido.getEndereco().getCidade(),
                pedido.getValorCompra(),
                produtos,
                pedido.getDataPedido(),
                pedido.getStatus()
        );

    }
    @Transactional
    public PedidoResponseDTO aceitarPedido(Integer pedidoId, Integer entregadorId) {
        int updated = pedidoRepository.aceitarPedido(pedidoId, entregadorId);
        if (updated == 0) {
            throw new PedidoJaAceitoException();
        }
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return PedidoParaListaPedidoResponse(pedido, "Entregador aceito");
    }
}
