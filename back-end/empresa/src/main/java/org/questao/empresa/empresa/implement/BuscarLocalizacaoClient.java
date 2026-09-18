package org.questao.empresa.empresa.implement;

import org.questao.empresa.empresa.dto.EmpresaCadastroDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "logistica")
public interface BuscarLocalizacaoClient {

    @PostMapping("/localizacao/empresa")
    EmpresaCadastroDTO buscarLocalizacao(@RequestBody EmpresaCadastroDTO dto);
}
