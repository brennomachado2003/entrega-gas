package org.questao.logistica.logistica.buscarLocalizacao.controller;




import org.questao.logistica.logistica.buscarLocalizacao.dto.CoordenadaDTO;
import org.questao.logistica.logistica.buscarLocalizacao.dto.EnderecoDTO;
import org.questao.logistica.logistica.buscarLocalizacao.service.BuscarLocalizacaoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buscar")
public class BuscarLocalizacaoController {

    private final BuscarLocalizacaoService buscarLocalizacaoService;

    public BuscarLocalizacaoController(
            BuscarLocalizacaoService buscarLocalizacaoService) {
        this.buscarLocalizacaoService = buscarLocalizacaoService;
    }

    @PostMapping
    public CoordenadaDTO buscarLatitudeLongitude(@RequestBody EnderecoDTO endereco) {

        return buscarLocalizacaoService.buscarLocalizacao(endereco);
    }
}
