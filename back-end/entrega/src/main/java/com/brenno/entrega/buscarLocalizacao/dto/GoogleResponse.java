package com.brenno.entrega.buscarLocalizacao.dto;
import lombok.Data;
import java.util.List;

@Data
public class GoogleResponse {
    private List<Result> results;
    private String status;
}
