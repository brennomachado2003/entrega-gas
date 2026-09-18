package org.questao.logistica.logistica.rota.service;

import org.questao.logistica.logistica.rota.dto.RotaDTO;
import org.questao.logistica.logistica.rota.dto.RotaRequestDTO;

public interface RotaService {

    RotaDTO calcularRota(RotaRequestDTO request);
}
