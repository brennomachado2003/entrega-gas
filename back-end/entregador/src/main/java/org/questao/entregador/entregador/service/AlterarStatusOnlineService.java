package org.questao.entregador.entregador.service;

import org.questao.entregador.Erros.ErroAoAlterarStatusOnlineService;
import org.questao.entregador.entregador.dominio.Entregador;
import org.questao.entregador.entregador.infraestrutura.EntregadorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AlterarStatusOnlineService {

    private static final Logger log = LoggerFactory.getLogger(AlterarStatusOnlineService.class);
    private final EntregadorRepository entregadorRepository;

    public AlterarStatusOnlineService(EntregadorRepository entregadorRepository) {
        this.entregadorRepository = entregadorRepository;
    }

    public Entregador alterarStatusOnline(Entregador entregador) {
        try {
            entregador.setAtivo(!entregador.getAtivo());
            return entregadorRepository.salvar(entregador);
        }
        catch (Exception ex) {
            log.error("Erro ao alterar status online: ", ex.getMessage());
            throw new ErroAoAlterarStatusOnlineService(ex);
        }
    }
}
