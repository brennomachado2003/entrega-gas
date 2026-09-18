package org.questao.logistica.logistica.rota.service;

import org.questao.logistica.logistica.rota.client.GoogleRoutesClient;
import org.questao.logistica.logistica.rota.dto.RotaDTO;
import org.questao.logistica.logistica.rota.dto.RotaRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class GoogleRotaService implements RotaService {

    private final GoogleRoutesClient client;

    public GoogleRotaService(GoogleRoutesClient client) {
        this.client = client;
    }

    @Override
    public RotaDTO calcularRota(RotaRequestDTO request) {
        return client.calcularRota(request);
    }
}
