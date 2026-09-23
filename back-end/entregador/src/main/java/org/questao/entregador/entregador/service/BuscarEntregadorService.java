package org.questao.entregador.entregador.service;

import org.questao.entregador.Erros.ErroAoBuscarEntregadorPeloId;
import org.questao.entregador.entregador.dominio.Entregador;
import org.questao.entregador.entregador.infraestrutura.EntregadorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BuscarEntregadorService {

    private final EntregadorRepository entregadorRepository;
    private static final Logger log = LoggerFactory.getLogger(BuscarEntregadorService.class);

    public BuscarEntregadorService(EntregadorRepository entregadorRepository) {
        this.entregadorRepository = entregadorRepository;
    }

    public Entregador buscarEntregadorPorId(Long id) {
        try {
            return entregadorRepository.buscar(id);
        }
        catch (Exception ex) {
            log.error("Erro ao tentar buscar entregador pelo id: ", ex.getMessage());
            throw new ErroAoBuscarEntregadorPeloId(ex);
        }
    }

}
