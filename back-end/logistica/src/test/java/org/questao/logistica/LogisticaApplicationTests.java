package org.questao.logistica;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.questao.logistica.logistica.rastreamento.controller.PosicaoEntregadorController;
import org.questao.logistica.logistica.rastreamento.dominio.PosicaoEntregador;
import org.questao.logistica.logistica.rastreamento.dto.AtualizarLocalizacaoDTO;
import org.questao.logistica.logistica.rastreamento.service.AtualizarLocalizacaoService;
import org.questao.logistica.logistica.rastreamento.service.BuscarPosicaoEntregadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PosicaoEntregadorController.class)
class PosicaoEntregadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AtualizarLocalizacaoService atualizarLocalizacaoService;

    @MockitoBean
    private BuscarPosicaoEntregadorService buscarPosicaoEntregadorService;

    @Test
    void deveAtualizarLocalizacaoComSucesso() throws Exception {
        Long id = 1L;

        AtualizarLocalizacaoDTO dto =
                new AtualizarLocalizacaoDTO(
                        -12.9714,
                        -38.5014
                );

        GeometryFactory geometryFactory = new GeometryFactory();

        Point ponto = geometryFactory.createPoint(
                new Coordinate(
                        -38.5014,
                        -12.9714
                )
        );

        PosicaoEntregador posicao =
                new PosicaoEntregador(
                        1L,
                        id,
                        ponto,
                        LocalDateTime.now()
                );

        when(atualizarLocalizacaoService.atualizarLocalizacaoEntregado(
                eq(id),
                any(AtualizarLocalizacaoDTO.class)
        )).thenReturn(posicao);

        mockMvc.perform(
                        post("/localizacao/{id}/localizacao", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto))
                )
                .andExpect(status().isOk());

        verify(atualizarLocalizacaoService)
                .atualizarLocalizacaoEntregado(
                        eq(id),
                        any(AtualizarLocalizacaoDTO.class)
                );
    }
}
