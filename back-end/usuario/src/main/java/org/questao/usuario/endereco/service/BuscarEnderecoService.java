package org.questao.usuario.endereco.service;

import org.questao.usuario.Erros.endereco.ErroAoBuscarEnderecoPeloId;
import org.questao.usuario.endereco.dominio.Endereco;
import org.questao.usuario.endereco.infraestrutura.EnderecoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BuscarEnderecoService {

    private static final Logger logger = LoggerFactory.getLogger(BuscarEnderecoService.class);
    private final EnderecoRepository enderecoRepository;

    public BuscarEnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco buscar(Long id) {
       try {
           return enderecoRepository.buscar(id);
       }
       catch (Exception e) {
           logger.error("Erro ao buscar endereco pelo identificador", e.getMessage());
           throw new ErroAoBuscarEnderecoPeloId(e);
       }
    }

}
