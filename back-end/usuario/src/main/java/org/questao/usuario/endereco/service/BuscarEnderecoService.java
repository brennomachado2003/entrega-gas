package org.questao.usuario.endereco.service;

import org.questao.usuario.endereco.dominio.Endereco;
import org.questao.usuario.endereco.infraestrutura.EnderecoRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarEnderecoService {

    private final EnderecoRepository enderecoRepository;

    public BuscarEnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco buscar(Long id) {
        return enderecoRepository.buscar(id);
    }

}
