package org.questao.logistica.logistica.buscarLocalizacao.dto;

import lombok.Data;

import java.util.List;

@Data
public class GoogleResponse {
    private List<Result> results;
    private String status;
}
