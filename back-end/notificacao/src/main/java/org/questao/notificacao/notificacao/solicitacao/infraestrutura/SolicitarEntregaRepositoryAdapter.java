package org.questao.notificacao.notificacao.solicitacao.infraestrutura;

import org.questao.notificacao.notificacao.solicitacao.dominio.SolicitacaoEntrega;
import org.questao.notificacao.notificacao.solicitacao.dominio.StatusSolicitacao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SolicitarEntregaRepositoryAdapter implements SolicitarEntregaRepository {

    private final SolicitacaoEntregaJPARepository solicitacaoEntregaJPARepository;

    public SolicitarEntregaRepositoryAdapter(SolicitacaoEntregaJPARepository solicitacaoEntregaJPARepository) {
        this.solicitacaoEntregaJPARepository = solicitacaoEntregaJPARepository;
    }

    @Override
    public List<SolicitacaoEntrega> listar() {
        List<SolicitacaoEntregaEntity> solicitacaoEntregaEntityList = solicitacaoEntregaJPARepository.findAll();
        return solicitacaoEntregaEntityList.stream().map(SolicitacaoEntregaMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public SolicitacaoEntrega buscar(Long id) {
        SolicitacaoEntregaEntity solicitacaoEntrega = solicitacaoEntregaJPARepository.findById(id).orElseThrow(() -> new IllegalArgumentException("solicitacao não encontrado: " + id));
        return SolicitacaoEntregaMapper.toDomain(solicitacaoEntrega);
    }

    @Override
    public SolicitacaoEntrega salvar(SolicitacaoEntrega solicitacaoEntrega) {
        SolicitacaoEntregaEntity solicitacaoEntregaEntity = SolicitacaoEntregaMapper.toEntity(solicitacaoEntrega);
        return SolicitacaoEntregaMapper.toDomain(solicitacaoEntregaJPARepository.save(solicitacaoEntregaEntity));
    }

    @Override
    public SolicitacaoEntrega atualizar(Long id, StatusSolicitacao dados) {
        SolicitacaoEntrega solicitacaoEntrega = buscar(id);
        solicitacaoEntrega.atualizarStatus(dados);
        return salvar(solicitacaoEntrega);
    }

    @Override
    public List<SolicitacaoEntrega> buscarListaSolicitacoesPendente(Long idEntrega) {
        List<SolicitacaoEntregaEntity> solicitacaoEntregaEntity = solicitacaoEntregaJPARepository.findByEntregadorAndStatus(idEntrega, StatusSolicitacao.PENDENTE);
        return solicitacaoEntregaEntity.stream().map(SolicitacaoEntregaMapper::toDomain).toList();
    }

    @Override
    public List<SolicitacaoEntrega> buscarListaSolicitacoesPedidoPendente(Long idPedido) {
        List<SolicitacaoEntregaEntity> solicitacaoEntregaEntityList = solicitacaoEntregaJPARepository.findByPedidoAndStatus(idPedido, StatusSolicitacao.PENDENTE);
        return solicitacaoEntregaEntityList.stream().map(SolicitacaoEntregaMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public void solicitacoesRecusadas(Long idPedido){
        solicitacaoEntregaJPARepository.atualizarStatusPendentes(idPedido, StatusSolicitacao.RECUSADA);
    }
}
